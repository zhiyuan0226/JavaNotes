package com.wzy.advice;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAdvice {

	public Object myarround(ProceedingJoinPoint p) throws Throwable {
		System.out.println("执行环绕");
		System.out.println("AspectJ环绕--前置");
		Object result = p.proceed();
		System.out.println("AspectJ环绕--后置");
		return result;
	}
}
