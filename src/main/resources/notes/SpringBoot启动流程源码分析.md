# SpringBoot启动流程源码分析

首先提出几个问题

* AutoConfiguration是如何做到的？
* @Configuration是如何工作的？
* @Autowire、@Value的属性是何时赋值的？能在构造函数里使用么？
* @Transactional、@Cachable 注解是如何工作的？

本文旨在走读SpringBoot启动流程源码，记录其中的关键节点，特别是业务开发过程中值得注意的点，一方面可以更好的理解SpringBoot的工作原理，一方面可以避免在日常开发中踩坑。



## 启动入口 SpringApplication.run

分析源码，首先要找到启动入口，SpringBoot应用启动入口即为SpringApplication#run静态方法：

```
@SpringBootApplication(scanBasePackages = {"com.xxx.yyy"})
public class Application {
    public static void main(String[] args) {
    		// 启动入口
        SpringApplication.run(Application.class, args);
    }
}
```

 继续跟踪run方法，发现2个关键节点：SpringApplication构造函数和run方法。

### SpringApplication构造函数

```
public SpringApplication(Object... sources) {
		initialize(sources);
}

private void initialize(Object[] sources) {
		if (sources != null && sources.length > 0) {
			this.sources.addAll(Arrays.asList(sources));
		}
		this.webEnvironment = deduceWebEnvironment();
		
		// 底层通过调用 SpringFactoriesLoader.loadFactoryNames 加载spring.factories中配置的 
		// ApplicationContextInitializer和ApplicationListener实现类，并通过反射构造instance
		setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
		setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
		
		this.mainApplicationClass = deduceMainApplicationClass();
}
```

构造函数中比较核心的关键点在于初识工具方法 SpringFactoriesLoader#loadFactoryNames

```
/**
 * 使用ClassLoader 加载所有 META-INF/spring.factories 文件，解析其格式，最后返回所有实现类名称List
 * spring.factories文件格式为：interfaceName=concreteClass1,concreteClass2,concreteClass3...
 */
public static List<String> loadFactoryNames(Class<?> factoryClass, ClassLoader classLoader) {
		String factoryClassName = factoryClass.getName();
		try {
		  // public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";
			Enumeration<URL> urls = (classLoader != null ? classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :
					ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
			List<String> result = new ArrayList<String>();
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				Properties properties = PropertiesLoaderUtils.loadProperties(new UrlResource(url));
				String propertyValue = properties.getProperty(factoryClassName);
				for (String factoryName : StringUtils.commaDelimitedListToStringArray(propertyValue)) {
					result.add(factoryName.trim());
				}
			}
			return result;
		}
		catch (IOException ex) {
			throw new IllegalArgumentException("Unable to load factories from location [" +
					FACTORIES_RESOURCE_LOCATION + "]", ex);
		}
	}

```

构造函数执行完后的SpringApplication，设置了initializers和listeners，这些属性非常重要，会完成一些重要工作。比如 ConfigFileApplicationListener，在接收到 ApplicationEnvironmentPreparedEvent 时，会处理 spring.profiles.active多环境配置。

<img src="images/image-20220211143434112.png" alt="image-20220211143434112" style="zoom:50%;" />



### SpringApplication.run（核心初始化方法）

```
# 省略一些无关紧要的初始化代码以及try-catch
public ConfigurableApplicationContext run(String... args) {
	  ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
		ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
		Banner printedBanner = printBanner(environment);
		context = createApplicationContext();
		analyzers = new FailureAnalyzers(context);
		prepareContext(context, environment, listeners, applicationArguments, printedBanner);
		refreshContext(context);
		afterRefresh(context, applicationArguments);
		listeners.finished(context, null);
		stopWatch.stop();
		if (this.logStartupInfo) {
			new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
		}
		return context;
	}
```



#### prepareEnvironment

主要工作为

* new StandardServletEnvironment()，初始化propertySources、propertyResolver、activeProfiles={},defaultProfiles={"default"}，初始化完的Environment propertySources属性来源主要有4个：

```
0. servletConfigInitParams
1. servletContextInitParams
2. systemProperties, 从System.getProperties()获取，系统属性，比如file.separator java.version，包括-D指定的一些属性。
3. systemEnvironment, 从System.getenv()获取，系统环境变量，比如JAVA_HOME CLASSPATH 这些在~/.bash_profile设置的变量。
```

* 通过 EventPublishingRunListener#environmentPrepared，发布 ApplicationEnvironmentPreparedEvent，会触发一个比较重要的ConfigFileApplicationListener#onApplicationEnvironmentPreparedEvent方法执行。

  ```
  经过这一步，propertySources新增了2个来源：
  0. servletConfigInitParams
  1. servletContextInitParams
  2. systemProperties
  3. systemEnvironment
  4. random：估计Spring内部使用的，无需关心
  5. ConfigFileApplicationListener$ConfigurationPropertySources   来自application.properties配置文件
  ```

#### 

####createApplicationContext

创建ApplicationContext，其类型是 AnnotationConfigEmbeddedWebApplicationContext。

```
// new AnnotationConfigEmbeddedWebApplicationContext();
public AnnotationConfigEmbeddedWebApplicationContext() {
		this.reader = new AnnotatedBeanDefinitionReader(this);
		this.scanner = new ClassPathBeanDefinitionScanner(this);
}

// new AnnotatedBeanDefinitionReader(this); 
这个构造函数向容器中注入了6个重要的基础 beanDefinition，广泛意义上都可以称为PostProcess，是在BeanFactory或者Bean实例化之后得到回调，进而完成一些工作。
1. ConfigurationClassPostProcessor, 是一个BeanDefinitionRegistryPostProcessor, 处理@Configuration
2. AutowiredAnnotationBeanPostProcessor, 是一个 InstantiationAwareBeanPostProcessor, 处理@Autowired和@Value
3. RequiredAnnotationBeanPostProcessor, 是一个InstantiationAwareBeanPostProcessor, 处理@Required
4. CommonAnnotationBeanPostProcessor, 是一个InstantiationAwareBeanPostProcessor,  JSR-250 support, 处理@Resource @PostConstruct @PreDestory
5. EventListenerMethodProcessor, 是一个SmartInitializingSingleton，用于处理@EventListener。
6. DefaultEventListenerFactory, 是一个EventListenerFactory, 与EventListenerMethodProcessor配置，用于创建ApplicationListener。

// new ClassPathBeanDefinitionScanner(this);
这个构造函数，创建了一个ClassPathBeanDefinitionScanner，默认的includeFilters=@Component
```



####prepareContext

准备Context，实则完成一些初始化操作，主要包括：

* 调用Initializers
* 创建BeanDefinitionLoader
* 注册主配置类（启动类Application）：beanName=application，
* 将SpringApplication的listener复制到ApplicationContext中、发布ApplicationPreparedEvent

```
// 调用Initializers。
applyInitializers(context);

// 创建BeanDefinitionLoader；将主配置类注册到BeanFactory
load(context, sources.toArray(new Object[sources.size()]));

// 将SpringApplication的listener复制到ApplicationContext中。发布ApplicationPreparedEvent。
// 上述两个功能，都是通过这个 EventPublishingRunListener 完成的。
listeners.contextLoaded(context);
```

![image-20220215130758656](images/image-20220215130758656.png)

#### refreshContext

大名鼎鼎的刷新容器，Spring的核心步骤，SpringBoot之前就已存在，具体实现为：AbstractApplicationContext#refresh。其核心主要包括如下几步：

* prepareRefresh：设置了一些标志位，无重要的关键点。

* prepareBeanFactory：

  * 注册BeanPostProcessor： ApplicationContextAwareProcessor
  * 注册BeanPostProcessor： ApplicationListenerDetector
  * 注册Bean：environment=StandardServletEnvironment
  * 注册Bean：systemProperties=System.getProperties()
  * 注册Bean：systemEnvironment=System.getenv()

* postProcessBeanFactory：注册了一个BeanPostProcessor：WebApplicationContextServletContextAwareProcessor

* invokeBeanFactoryPostProcessors：调用BeanFactoryPostProcessor的钩子方法，Bean扫描就是在这一步完成的，具体完成Bean扫描的为这个BeanFactoryPostProcessor：ConfigurationClassPostProcessor#postProcessBeanDefinitionRegistry

  <img src="images/image-20220212204936372.png" alt="image-20220212204936372" style="zoom:30%;" />

* registerBeanPostProcessors

* registerListeners

* finishBeanFactoryInitialization：初始化所有的单实例Bean

  * createBean：构造函数实例化Bean

  * populateBean：初始化Bean属性，包括 @Autowired和@Value的处理

  * BeanPostProcessor#before：before钩子

  * invokeInitMethods：各种init方法，init-method、@PostConstruct 、InitializationBean

  * BeanPostProcessor#after：after钩子

    

####AutoConfiguration工作原理

```
# spring.factories 文件加载
List<String> classNames = SpringFactoriesLoader.loadFactoryNames(type, classLoader)
```



####  ConfigFileApplicationListener

ConfigFileApplicationListener是一个ApplicationListener，完成了一些值得注意的工作：

* 从spring.factories中加载并执行 EnvironmentPostProcessor
* 从spring.factories中加载 PropertySourceLoader，用于加载 applicaiton.properties 文件
* 处理 spring.profiles.active 



##### 加载并执行 EnvironmentPostProcessor

```
private void onApplicationEnvironmentPreparedEvent(ApplicationEnvironmentPreparedEvent event) {
		List<EnvironmentPostProcessor> postProcessors = loadPostProcessors();
		postProcessors.add(this);
		AnnotationAwareOrderComparator.sort(postProcessors);
		for (EnvironmentPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessEnvironment(event.getEnvironment(), event.getSpringApplication());
		}
	}

// 从 spring.factories 中加载 EnvironmentPostProcessor 实现类
List<EnvironmentPostProcessor> loadPostProcessors() {
		return SpringFactoriesLoader.loadFactories(EnvironmentPostProcessor.class, getClass().getClassLoader());
}

```



#####PropertySourceLoader

<img src="images/image-20220211160447009.png" alt="image-20220211160447009" style="zoom:40%;" />



##### 处理spring.profiles.active 

具体处理的细节代码非常绕，无论是看类名还是方法名，读起来根本想不到它是处理Profile的。

这么重要的功能藏得太深，而且是通过反射设置的，阅读起来日了狗，这一块个人感觉写的是非常烂。

我们可以简单的理解为，解析application.properties文件，匹配到 spring.profiles.active属性，设置到Environment.activeProfiles中，再解析application-xxx.properties

```
// ConfigFileApplicationListener#bindSpringProfiles
// 读取application.properties，过滤其中以spring.profiles开头的属性，最终设置到Environment中的activeProfiles中
private SpringProfiles bindSpringProfiles(PropertySources propertySources) {
			SpringProfiles springProfiles = new SpringProfiles();
			RelaxedDataBinder dataBinder = new RelaxedDataBinder(springProfiles, "spring.profiles");
			// 经过dataBinder.bind，会初始化springProfiles中的active和include属性
			dataBinder.bind(new PropertySourcesPropertyValues(propertySources, false));
			springProfiles.setActive(resolvePlaceholders(springProfiles.getActive()));
			springProfiles.setInclude(resolvePlaceholders(springProfiles.getInclude()));
			return springProfiles;
}
```

<img src="images/image-20220211164838553.png" alt="image-20220211164838553" style="zoom:30%;" />



<img src="images/image-20220211165207789.png" alt="image-20220211165207789" style="zoom:30%;" />







