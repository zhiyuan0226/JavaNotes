package com.wzy.advice;

import org.springframework.aop.ThrowsAdvice;

public class MyThrowAdvice implements ThrowsAdvice {

	public void afterThrowing(Exception ex) throws Throwable { 
		
		System.out.println("执行异常通过-schema-base 方式 "); 
	}
	
	public void myThrowingAspectJ(){ 
		
		System.out.println("执行异常通过-AspectJ- 方式 "); 
	}
}
