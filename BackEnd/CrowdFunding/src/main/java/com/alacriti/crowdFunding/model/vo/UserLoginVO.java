package com.alacriti.crowdFunding.model.vo;

public class UserLoginVO {
	private int id;
	private String userName;
	private String userEmail;
	UserLoginVO(String userName,String userEmail){
		this.userName=userName;
		this.userEmail=userEmail;
	}
	UserLoginVO(){}
	UserLoginVO(int id,String userEmail)
	{
		this.id=id;
		this.userEmail=userEmail;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
