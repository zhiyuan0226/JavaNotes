Spring学习
	

1.轮子理论
	不要重复发明轮子(直接使用写好的代码)
	spring框架的宗旨：不重新发明技术，让原有技术使用起来更加方便

2.
	Ioc 解耦
	AOP 扩展
	事务管理

3.IoC 控制反转
	1.IoC是什么？
		原先由程序员主动通过new实例化的事情交给spring来管理
	2.作用：解耦
		解除了程序员与对象之间的耦合

4.环境搭建(导入jar包)
	4个核心 1个依赖
	beans、core、context、expression
	commons-loggins

5.编写配置文件applicationContext.xml
	通过bean标签来创建对象(默认是配置文件被加载时创建对象)
		<?xml version="1.0" encoding="UTF-8"?>
		<beans xmlns="http://www.springframework.org/schema/beans"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://www.springframework.org/schema/beans
				http://www.springframework.org/schema/beans/spring-beans.xsd">
			<!-- 
				id表示获取到对象标识
				 class 创建哪个类的对象
			 -->
			<bean id="peo" class="com.wzy.pojo.People"/>
		</beans>
		
	测试方法中使用
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		// 参数：<Bean>标签的id   返回值类型(默认为object)
		People peo = ac.getBean("peo",People.class);
	来获取对象

6.创建对象的方式
	1.通过构造方法创建(无参构造、有参构造)
	2.实例工厂
	3.静态工厂
	
	1.如果匹配多个构造方法(默认执行最后的构造方法)
	
	工厂设计模式：帮助创建类对象，一个工厂可以生产多个对象
	2.实例工厂
		需要创建工厂，才能生产对象
		
	3.静态工厂(在创建对象的方法前面添加static关键字)
		不需要创建工厂,快速创建对象.
	
	applicationContext.xml
		<?xml version="1.0" encoding="UTF-8"?>
		<beans xmlns="http://www.springframework.org/schema/beans"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://www.springframework.org/schema/beans
				http://www.springframework.org/schema/beans/spring-beans.xsd">
			<!-- 
				id表示获取到对象标识
				 class 创建哪个类的对象
			 -->
			<bean id="peo" class="com.wzy.pojo.People"/>
			 <bean id="peo1" class="com.wzy.pojo.People">
				<constructor-arg index="0" value="666" ></constructor-arg>
				<constructor-arg index="1" value="zhiyuan"></constructor-arg>
			 </bean>
			 <bean id="peo2" class="com.wzy.pojo.People">
				<constructor-arg name="id" value="666" ></constructor-arg>
				<constructor-arg index="1" value="zhiyuan"></constructor-arg>
			 </bean>
			 <!-- 创建实例工厂 -->
			 <bean id="factory" class="com.wzy.pojo.PeopleFactory"></bean>
			 <!-- 实例工厂创建对象 -->
			 <bean id="peo3" factory-bean="factory" factory-method="newInstance"></bean>
			 
			 <!-- 静态工厂创建对象 -->
			 <bean id="peo4" class="com.wzy.pojo.PeopleFactory" factory-method="newInstance1"></bean>
			 
		</beans>
	
		构造工厂的代码
			public class PeopleFactory {

				public People newInstance() {
					return new People();
				}
				
				// 静态实例
				public static People newInstance1() {
					return new People();
				}
			}
		测试代码
			public class Test {
				public static void main(String[] args) {
					
					
					ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
					// 无参构造
					People peo = ac.getBean("peo",People.class);
					// 有参构造
					People peo1 = ac.getBean("peo1",People.class);
					People peo2 = ac.getBean("peo1",People.class);

					// 实例工厂创建对象
					People peo3 = ac.getBean("peo3",People.class);
					
					// 静态工厂创建对象
					People peo4 = ac.getBean("peo4",People.class);
					System.out.println(peo4);
				}
			}

7.给Bean的属性赋值(注入)
	1.通过构造方法来注入
	2.设置注入(通过set方法来注入)

8.DI 依赖注入
	DI:当一个对象(A)中需要依赖另一个对象(B)时，把另一个对象(B)赋值给这个对象(A)的过程叫做DI

9.Spring来简化Mybatis
	1.导包
		Mybatis需要的jar包
		Spring运行需要的包
		spring-jdbc、mybatis-spring、spring-tx、spring-aop、spring-web
	2.写配置文件
		dataSource
		sqlSessionFactory
		MapperScannerConfigurer(mybatis.xml下的mappper标签下的package属性)
		注意：
			给属性注入需要spring管理属性所在类
			如果要上传至tomcat，需要编写web.xml文件
			spring无法管理Servlet
				<!-- 上下文参数 -->
				<context-param> <param-name>contextConfigLocation</param-name>
				<!-- spring 配置文件 -->
				<param-value>classpath:applicationContext.xml</para m-value> </context-param>
		
10.AOP 面向切面编程
	1.正常执行顺序：纵向流程
						demo1()
						demo2()
						demo3()
	2.AOP 
		
						demo1()
			前置通知	demo2()		后置通知
						demo3()	
		在程序原有纵向流程中，针对某一个或者某些方法添加通知，形成横切面过程就叫做面向切面编程。
			高扩展性
			相当于释放了部分逻辑，让职责更加明确
	3.常用概念
		1.原有功能：切点
		2.前置通知 beforeAdvice
		3.后置通知 afterAdvice
		4.异常通知 throwAdvice
		5.所有功能总称叫做切面
		6.织入 把切面嵌入原有功能叫做织入
	4.实现方式
		1.schame-base方式
			每个通知需要实现接口或者类(MathBeforeAdvice、AfterReturningAdvice)
			配置spring文件时是在<aop:config>中配置
		2.aspectJ方式
			每个通知不需要实现接口或者类
			配置spring文件时是在<aop:config>标签的子标签<aop:aspect>中配置
	Schame-base实现代码
		4.实现前置通知	
			public class MyBeforeAdvice implements MethodBeforeAdvice{

				@Override
				public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
					// TODO Auto-generated method stub
					System.out.println("执行前置通知");
				}
			}
		5.实现后置通知
			public class MyAfterAdvice implements AfterReturningAdvice {

				@Override
				public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
					// TODO Auto-generated method stub
					System.out.println("执行后置通知！");
				}

			}
		
		6.applicationContext.xml
			<?xml version="1.0" encoding="UTF-8"?>
			<beans xmlns="http://www.springframework.org/schema/beans"
				xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				xmlns:aop="http://www.springframework.org/schema/aop"
				xsi:schemaLocation="http://www.springframework.org/schema/beans
					http://www.springframework.org/schema/beans/spring-beans.xsd
					http://www.springframework.org/schema/aop
					http://www.springframework.org/schema/aop/spring-aop.xsd">
				<!-- 
					aop配置
				 -->
				<aop:config>
					<!-- 定义切点 -->
					<aop:pointcut expression="execution(* com.wzy.test.Demo.demo2())" id="mypoint"/>
					<!-- 引入通知 -->
					<aop:advisor advice-ref="myBeforeAdvice" pointcut-ref="mypoint"/>
					<aop:advisor advice-ref="myAfterAdvice" pointcut-ref="mypoint"/>
				</aop:config>
				
				<!-- 上面引入通知的时候需要注入通知类 -->
				<bean id="myBeforeAdvice" class="com.wzy.advice.MyBeforeAdvice"></bean>
				<bean id="myAfterAdvice" class="com.wzy.advice.MyAfterAdvice"></bean>
				
				<!-- 需要将引入通知方法的对象交给spring来管理，测试的时候使用 -->
				<bean id="demo" class="com.wzy.test.Demo"></bean>
			</beans>
	6.配置异常通知
		只有切点报了异常才能触发异常通知，否者无效
		使用了try-catch处理之后也不会触发
		1.schema-base方法
			新建一个类实现ThrowsAdvice接口
			必须重写afterThrowing()方法 
			参数必须是一个或者四个
				public class MyThrowAdvice implements ThrowsAdvice {
					public void afterThrowing(Exception ex) throws Throwable { 					
						System.out.println("执行异常通过-schema-base 方式 "); 
					}
				}	
		2.AspectJ方式	
			新建任意类 任意方法
			public class MyThrowAdvice{ 
				public void myexception(Exception e1){ 	
					System.out.println("执行异常通知 "+e1.getMessage()); 
				} 
			}
		applicationContext.xml文件配置
			<aop:config>
				<!-- Schema-base方式 -->
				<aop:pointcut expression="execution(* com.wzy.test.*.*(..))" id="mypoint"/>
				<aop:advisor advice-ref="myThrowAdvice" pointcut-ref="mypoint"/>
				
				<!-- AspectJ方式 -->
				<aop:aspect ref="myThrowAdvice1">
					<aop:pointcut expression="execution(* com.wzy.test.*.*(..))" id="mypoint1"/>
					<aop:after-throwing method="myThrowingAspectJ" pointcut-ref="mypoint1" />
				</aop:aspect>
			</aop:config>
			
			<!-- AspectJ方式 -->
			<bean id="myThrowAdvice1" class="com.wzy.advice.MyThrowAdvice"></bean>
			<!-- Schema-base方式 -->
			<bean id="myThrowAdvice" class="com.wzy.advice.MyThrowAdvice"></bean>
			
			
	7.环绕通知：那前置通知和后置通知都写在一个通知中
		
		1.schema-base
			public class MyAroundAdvice implements MethodInterceptor{
				@Override
				public Object invoke(MethodInvocation arg0) throws Throwable {
					// TODO Auto-generated method stub
					System.out.println("环绕---前置通知");
					Object result = arg0.proceed(); // 相当于放行
					System.out.println("环绕---后置通知");
					return result;
				}

			}
		2.AspectJ
			public class MyAdvice {
				public Object myarround(ProceedingJoinPoint p) throws Throwable {
					System.out.println("执行环绕");
					System.out.println("AspectJ环绕--前置");
					Object result = p.proceed();
					System.out.println("AspectJ环绕--后置");
					return result;
				}
			}
	8.使用注解配置AOP
		由于spring不知道哪些包里面的类可能有注解
		因此需要扫描包
			<!-- 扫描可能有注解的包  多个包用逗号隔开-->
			<context:component-scan base-package="com.wzy.advice,com.wzy.test"></context:component-scan>
						
			
11.自动注入

12.加载属性文件
	<context:property-placeholder location="classpath:db.properties"/>
	
	在取的时候${jdbc.username}但是要保证sqlSessionfactor类对象id为factory
			
			
			
			
			
			
			
			
			
			
			
			
			
			