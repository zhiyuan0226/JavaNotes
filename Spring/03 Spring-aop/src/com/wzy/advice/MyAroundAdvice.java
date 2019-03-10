package com.wzy.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

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
