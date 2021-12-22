package cc.lzy.spring.consurrent;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

/**
 * class function desc
 *
 * @author taigai
 * @date 2021/12/22 4:57 PM
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor{


    private static final Logger log = LoggerFactory.getLogger(TraceThreadPoolExecutor.class);

    /**
     * 默认超时时间30s
     */
    private static final long DEFAULT_TASK_TIMEOUT = 30000;

    /**
     * 日期格式化对象
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 线程池名称
     */
    private String threadPoolName;

    /**
     * 任务执行上下文Map
     */
    private Map<Runnable, ExecutionContext> executingContextMap = new ConcurrentHashMap<>();
    /**
     * 定时调度器
     */
    private ScheduledExecutorService scheduler           = new ScheduledThreadPoolExecutor(1,
        new CustomizableThreadFactory("pool-monitor-scheduler-"));

    /**
     * 线程池构造参数
     *
     * @param threadPoolName  线程池名称
     * @param corePoolSize    核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime   空闲线程存活时间
     * @param unit            时间单位
     * @param workQueue       工作队列
     * @param threadFactory   线程创建工厂
     * @param handler         拒绝执行策略
     */
    public TraceThreadPoolExecutor(String threadPoolName,
        int corePoolSize,
        int maximumPoolSize,
        long keepAliveTime,
        TimeUnit unit,
        BlockingQueue<Runnable> workQueue,
        ThreadFactory threadFactory,
        RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        this.threadPoolName = threadPoolName;
        startSchedule();
    }

    /**
     * 执行任务之前钩子
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        executingContextMap.put(r, new ExecutionContext(t));
    }

    /**
     * 执行任务之后的钩子
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        executingContextMap.remove(r);
    }

    /**
     * @see ThreadPoolExecutor#newTaskFor(Runnable, Object)
     */
    @Override
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new FutureTask<T>(runnable, value) {
            @Override
            public void run() {
                // set trace
                // xxx
                try {
                    super.run();
                } finally {
                    // clear trace
                    // xxx
                }
            }
        };
    }

    /**
     * @see ThreadPoolExecutor#newTaskFor(Callable)
     */
    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new FutureTask<T>(callable) {
            @Override
            public void run() {
                // set trace
                // xxx
                try {
                    super.run();
                } finally {
                    // clear trace
                    // xxx
                }
            }
        };
    }

    /**
     * 开始调度
     */
    private void startSchedule() {
        scheduler.scheduleAtFixedRate(this::printPoolStat, 5, 5, TimeUnit.SECONDS);
    }

    private void printPoolStat() {
        try {
            doPrintPoolStat();
        } catch (Throwable t) {
            log.info("ThreadPool '{}' is interrupted when running: ", this.threadPoolName,
                t);
        }
    }

    /**
     * 打印线程池运行时信息
     */
    private void doPrintPoolStat() {
        int decayedTaskCount = 0;
        for (Map.Entry<Runnable, ExecutionContext> e : executingContextMap.entrySet()) {
            Runnable task = e.getKey();
            ExecutionContext context = e.getValue();
            long executionTime = System.currentTimeMillis() - context.getTaskKickOffTime();
            if (executionTime > DEFAULT_TASK_TIMEOUT) {
                decayedTaskCount++;
                // 已经打印警告信息，则停止打印
                if (context.isPrintedWarnMessage()) {
                    continue;
                }
                // 打印超时任务堆栈
                context.setPrintedWarnMessage(true);
                StackTraceElement[] stackTrace = context.getThread().getStackTrace();
                StringBuilder sb = new StringBuilder();
                for (StackTraceElement ele : stackTrace) {
                    sb.append("\t" + ele + "\n");
                }
                String traceId = getTraceId(context.getThread());
                log.warn(
                    "[TIMEOUT] Task {} in thread pool {} started on {}{} exceeds the limit of {} execution time with "
                        + "stack trace:\n    {}",
                    task,
                    threadPoolName,
                    DATE_FORMAT.format(context.getTaskKickOffTime()),
                    traceId == null ? "" : " with traceId " + traceId,
                    "30s",
                    sb.toString().trim());
            }
        }

        // #threadPoolName, #executing, #idle, #pool, #queue, #decayed
        log.info("[{}] [#executing,#idle,#pool,#queue,#decayed] [{},{},{},{},{}]",
            threadPoolName,
            executingContextMap.size(),
            this.getPoolSize() - executingContextMap.size(),
            this.getPoolSize(),
            this.getQueue().size(),
            decayedTaskCount);

    }

    private static String getTraceId(Thread t) {
        try {
            // 反射获取线程 threadLocals 成员，类型为ThreadLocal.ThreadLocalMap
            Object threadLocals = ClassUtils.getFieldVal("threadLocals", t);
            if (threadLocals == null) {
                return null;
            }

            // 反射获取ThreadLocalMap的 table 成员，类型为Array
            Object[] table = ClassUtils.getFieldVal("table", threadLocals);
            if (table == null || table.length == 0) {
                return null;
            }

            // 遍历获取trace上下文
            for (Object o : table) {
                if (o == null) {
                    continue;
                }
                Object value = ClassUtils.getFieldVal("value", o);
                // 判断是否为RpcContext实例，获取traceId
                //if (value instanceof RpcContext_inner) {
                //    return ((RpcContext_inner)value).getTraceId();
                //}
            }
        } catch (Throwable e) {
            return null;
        }
        return null;
    }

    private static class ExecutionContext {
        /**
         * 执行任务的线程
         */
        private Thread  thread;
        /**
         * 是否已打印超时
         */
        private boolean printedWarnMessage;
        /**
         * 任务开始执行时间
         */
        private long    taskKickOffTime;

        /**
         * Getter method for property <tt>thread</tt>.
         *
         * @return property value of thread
         */
        public Thread getThread() {
            return thread;
        }

        /**
         * Setter method for property <tt>thread</tt>.
         *
         * @param thread value to be assigned to property thread
         */
        public void setThread(Thread thread) {
            this.thread = thread;
        }

        /**
         * Getter method for property <tt>printedWarnMessage</tt>.
         *
         * @return property value of printedWarnMessage
         */
        public boolean isPrintedWarnMessage() {
            return printedWarnMessage;
        }

        /**
         * Setter method for property <tt>printedWarnMessage</tt>.
         *
         * @param printedWarnMessage value to be assigned to property printedWarnMessage
         */
        public void setPrintedWarnMessage(boolean printedWarnMessage) {
            this.printedWarnMessage = printedWarnMessage;
        }

        /**
         * Getter method for property <tt>taskKickOffTime</tt>.
         *
         * @return property value of taskKickOffTime
         */
        public long getTaskKickOffTime() {
            return taskKickOffTime;
        }

        /**
         * Setter method for property <tt>taskKickOffTime</tt>.
         *
         * @param taskKickOffTime value to be assigned to property taskKickOffTime
         */
        public void setTaskKickOffTime(long taskKickOffTime) {
            this.taskKickOffTime = taskKickOffTime;
        }

        ExecutionContext(Thread thread) {
            this.thread = thread;
            this.taskKickOffTime = System.currentTimeMillis();
            printedWarnMessage = false;
        }
    }
}