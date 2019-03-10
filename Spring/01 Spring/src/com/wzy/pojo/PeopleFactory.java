package com.wzy.pojo;

public class PeopleFactory {

	public People newInstance() {
		return new People();
	}
	
	// 静态实例
	public static People newInstance1() {
		return new People();
	}
}
