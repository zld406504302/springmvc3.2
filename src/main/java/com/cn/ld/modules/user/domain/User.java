package com.cn.ld.modules.user.domain;

import com.cn.ld.base.domain.BaseLongIdDomain;

public class User extends BaseLongIdDomain {

	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
    private int age  ;
    private char sex ;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

}
