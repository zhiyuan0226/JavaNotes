package com.wzy.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class MyAfterAdvice implements AfterReturningAdvice {
	
	/**
	 *  arg0: 切点方法返回值 
	 *  arg1: 切点方法对象 
	 *  arg2: 切点方法参数 
	 *  arg3: 切点方法所在类的对象
	 */
	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("执行后置通知！");
	}

}
