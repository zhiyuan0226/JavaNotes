Spring学习

1.轮子理论
	不要重复发明轮子(直接使用写好的代码)
	spring框架的宗旨：不重新发明技术，让原有技术使用起来更加方便

2.

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
		
10.









