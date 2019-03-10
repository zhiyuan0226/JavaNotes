package com.wzy.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wzy.pojo.People;
import com.wzy.service.impl.PeopleServiceImpl;

public class Test {
	public static void main(String[] args) {
		// 创建spring容器
		ApplicationContext ac =new 	ClassPathXmlApplicationContext("applicationContext.xml");
		
		// 查看spring管理的对象
		String[] names = ac.getBeanDefinitionNames();
		for (String string : names) {
			System.out.println(string);
		}
		
		// 测试是否成功
		PeopleServiceImpl bean = ac.getBean("peopleService",PeopleServiceImpl.class);
		List<People> show = bean.show();
		for (People people : show) {
			System.out.println(people);
		}
	}
}
