package com.gqx.c_onetomany;

public class IdCard {
	private String cardNum;	//���֤�ţ�����Ψһ��ʶ
	private String place;	//���֤��ַ
	//���֤���û�һ��һ�Ĺ�ϵ
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
