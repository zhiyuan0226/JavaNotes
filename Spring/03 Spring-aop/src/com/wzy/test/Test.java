package com.wzy.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac =new ClassPathXmlApplicationContext("applicationContext.xml");
		Demo bean = ac.getBean("demo",Demo.class);
//		bean.demo1();
//		bean.demo2();
//		bean.demo3();
		bean.demo4("zhiyuan");
//		Demo1 bean1 = ac.getBean("demo1",Demo1.class);
//		bean1.demo1();
	}
}
