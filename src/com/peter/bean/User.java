package com.peter.bean;

public class User {
	private String id;
	private String username;
	private String password;
	private String imgUrl;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + 
				", password=" + password + ", imgUrl=" + imgUrl + "]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
