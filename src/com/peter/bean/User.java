package com.peter.bean;

public class User {
	private String uId;
	private String username;
	private String password;
	private String uImgUrl;
	
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
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
	public String getuImgUrl() {
		return uImgUrl;
	}
	public void setuImgUrl(String uImgUrl) {
		this.uImgUrl = uImgUrl;
	}
}
