package com.wzy.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wzy.pojo.People;
import com.wzy.pojo.PeopleFactory;

public class Test {
	public static void main(String[] args) {
		
		
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
// 创建对象的三种方法
		// 无参构造
		People peo = ac.getBean("peo",People.class);
		// 有参构造
		People peo1 = ac.getBean("peo1",People.class);
		People peo2 = ac.getBean("peo1",People.class);

		// 实例工厂创建对象
		People peo3 = ac.getBean("peo3",People.class);
		
		// 静态工厂创建对象
		People peo4 = ac.getBean("peo4",People.class);

// 属性注入
		// 有参构造注入
		
		// 设置注入
		People peo5 = ac.getBean("peo5",People.class);
		
		System.out.println(peo5);
	}
}
