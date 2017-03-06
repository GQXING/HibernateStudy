package com.gqx.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * javaBean�����
 * @author ������
 *
 */
public class User {
	
	private int userId;
	private String userName;
	//һ���û���Ӧ�����ַ
	private Set<String> address;
	private List<String> addressList=new ArrayList<String>();
	private Map<String, String> addressMap=new HashMap<String, String>();
	
	
	public void setAddressMap(Map<String, String> addressMap) {
		this.addressMap = addressMap;
	}
	public Map<String, String> getAddressMap() {
		return addressMap;
	}
	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}
	public List<String> getAddressList() {
		return addressList;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Set<String> getAddress() {
		return address;
	}
	public void setAddress(Set<String> address) {
		this.address = address;
	}
	
}
