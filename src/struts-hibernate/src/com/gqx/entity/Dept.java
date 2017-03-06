package com.gqx.entity;

import java.util.HashSet;
import java.util.Set;

/*	异常 org.hibernate.InstantiationException: No default constructor for entity 
	当查询的时候返回的实体类是一个对象实例，是hibernate动态通过反射生成的 
	反射的Class.forName("className").newInstance();需要对应的类提供一个无参构造函数,
	出现这种问题是一般都是实体类有一个有参的构造 ，没有无参构造
	
	下面说一下有参构造 和无参构造
	1，每一个类在默认情况下，都会有一个无参数构造器！
	2，当声明了带参构造器，那么无参构造器就不存在了，需要无参构造器，就必须重新声明！
	3，构造器是不能够继承的
	4，在类构造器中，无论是有参数还是无参数构造器，默认情况下，首先会调用父类的无参构造器(一直到最上层的父类)，
	这是因为要调用父类继承下来的方法必须要先初始化父类.
	5，需要在子类构造器中调用父类其它的构造器，可以显示声明*/

public class Dept {
	private int deptId;
	private String deptName;
	//一对多配置的集合，即一个部门对应多个员工
	private Set<Employee> emps=new HashSet<Employee>();

	
	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Set<Employee> getEmps() {
		return emps;
	}

	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}



}
