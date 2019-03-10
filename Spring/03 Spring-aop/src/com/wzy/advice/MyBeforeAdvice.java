package com.wzy.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class MyBeforeAdvice implements MethodBeforeAdvice{
	/**
	 * arg0: 切点方法对象 Method 对象
	 * arg1: 切点方法参数
	 * arg2: 切点在哪个对象
	 */
	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("执行前置通知！");
	}

}
