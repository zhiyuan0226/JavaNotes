package com.wzy.pojo;

public class People {
	private int id;
	private String name;
	
	public People() {
		super();
	}
	public People(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "People [id=" + id + ", name=" + name + "]";
	}
	
}
