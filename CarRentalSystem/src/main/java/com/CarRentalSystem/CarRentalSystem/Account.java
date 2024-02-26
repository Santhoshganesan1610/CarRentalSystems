package com.CarRentalSystem.CarRentalSystem;

public class Account{
	private String user_name;
	private String Password;
	public Account(String user_name, String password) {
		this.user_name = user_name;
		Password = password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
