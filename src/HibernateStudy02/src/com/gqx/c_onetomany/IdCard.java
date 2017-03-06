package com.gqx.c_onetomany;

public class IdCard {
	private String cardNum;	//身份证号，对象唯一标识
	private String place;	//身份证地址
	//身份证与用户一对一的关系
	private User user;
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
