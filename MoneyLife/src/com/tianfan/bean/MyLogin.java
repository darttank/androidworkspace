package com.tianfan.bean;

public class MyLogin {
	
	public String username;
	public String password;
	
	public MyLogin(String username,String password)
	{
		this.username = username;
		this.password = password;
	}
	 public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
