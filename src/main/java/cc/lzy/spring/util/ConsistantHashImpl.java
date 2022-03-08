package cc.lzy.spring.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.util.DigestUtils;

/**
 * class function desc
 *
 * @author taigai
 * @date 2022/03/08 5:48 PM
 */
public class ConsistantHashImpl {

    private static final int BUCKET = 128;
    private static final String PREFIX = "NO.";

    private static AtomicReference<Set<String>> serverSet = new AtomicReference<>();
    private static AtomicReference<HashItem[]> items = new AtomicReference<>();
    private static AtomicBoolean isInit = new AtomicBoolean(false);

    public static synchronized void init(String[] servers) {
        if (isInit.get()) {
            return;
        }

        // 初始化server set
        Set<String> newServers = new HashSet<>(servers.length * 2);
        for (String server : servers) {
            newServers.add(server);
        }
        serverSet.set(newServers);

        // 初始化 hash item
        HashItem[] newItems = new HashItem[servers.length * BUCKET];
        int index = 0;
        for (String server : servers) {
            for (int j = 0; j < BUCKET; j++) {
                newItems[index++] = new HashItem(server, genHashCode(server, j));
            }
        }
        Arrays.sort(newItems);
        items.set(newItems);

        // init flag
        isInit.set(true);
    }


    public static String getServerByPosition(int position) {
        if (!isInit.get()) {
            throw new IllegalStateException("not init!");
        }

        int index = Arrays.binarySearch(items.get(), new HashItem("", position));
        // 没找到具体值，binarySearch会返回应该插入的位置的负数
        if (index < 0) {
            index = -index - 1;
        }

        if (index >= items.get().length) {
            index = items.get().length - 1;
        }
        return items.get()[index].server;
    }


    public synchronized static void addServer(String server) {
        if (!isInit.get()) {
            throw new IllegalStateException("not init!");
        }

        HashItem[] newItems = new HashItem[items.get().length + BUCKET];
        int index = 0;
        for (HashItem item : items.get()) {
            newItems[index++] = item;
        }
        for (int i = 0; i < BUCKET; i++) {
            newItems[index++] = new HashItem(server, i);
        }

        Arrays.sort(newItems);
        items.set(newItems);
    }

    public synchronized static void removeServer(String server) {
        if (!isInit.get()) {
            throw new IllegalStateException("not init!");
        }

        HashItem[] newItems = new HashItem[items.get().length - BUCKET];
        int index = 0;
        for (HashItem item : items.get()) {
            if (!item.getServer().equals(server)) {
                newItems[index++] = item;
            }
        }
        items.set(newItems);
    }

    public synchronized static void clear() {
        if (!isInit.get()) {
            throw new IllegalStateException("not init!");
        }
        serverSet.set(new HashSet<>());
        items.set(new HashItem[0]);
        isInit.set(false);
    }


    public static Set<String> getServerSet() {
        return serverSet.get();
    }

    private static int genHashCode(String server, int seq) {
        return Math.abs(DigestUtils.md5DigestAsHex((server + PREFIX + seq).getBytes()).hashCode());
    }

    private static class HashItem implements Comparable<HashItem> {
        private String server;
        private int hashCode;

        public HashItem(String server, int hashCode) {
            this.server = server;
            this.hashCode = hashCode;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public int getHashCode() {
            return hashCode;
        }

        public void setHashCode(int hashCode) {
            this.hashCode = hashCode;
        }

        @Override
        public int compareTo(HashItem o) {
            return this.hashCode - o.hashCode;
        }
    }
}