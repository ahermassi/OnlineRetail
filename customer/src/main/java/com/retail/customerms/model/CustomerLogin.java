package com.retail.customerms.model;

public class CustomerLogin {
	
	private String username;
	private String password;
	
	
	
	public CustomerLogin() {
		super();
	}

	public CustomerLogin(String username, String password) {
		super();
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

	@Override
	public String toString() {
		return "CustomerLogin [username=" + username + ", password=" + password + "]";
	}
}
