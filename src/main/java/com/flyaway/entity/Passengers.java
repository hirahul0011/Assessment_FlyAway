package com.flyaway.entity;

public class Passengers {
	
	public String name;
	public String sex;
	public int age;
	
	public Passengers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Passengers(String name, String sex, int age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	

}
