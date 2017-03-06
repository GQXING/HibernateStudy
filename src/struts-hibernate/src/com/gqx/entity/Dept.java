package com.gqx.entity;

import java.util.HashSet;
import java.util.Set;

/*	�쳣 org.hibernate.InstantiationException: No default constructor for entity 
	����ѯ��ʱ�򷵻ص�ʵ������һ������ʵ������hibernate��̬ͨ���������ɵ� 
	�����Class.forName("className").newInstance();��Ҫ��Ӧ�����ṩһ���޲ι��캯��,
	��������������һ�㶼��ʵ������һ���вεĹ��� ��û���޲ι���
	
	����˵һ���вι��� ���޲ι���
	1��ÿһ������Ĭ������£�������һ���޲�����������
	2���������˴��ι���������ô�޲ι������Ͳ������ˣ���Ҫ�޲ι��������ͱ�������������
	3���������ǲ��ܹ��̳е�
	4�����๹�����У��������в��������޲�����������Ĭ������£����Ȼ���ø�����޲ι�����(һֱ�����ϲ�ĸ���)��
	������ΪҪ���ø���̳������ķ�������Ҫ�ȳ�ʼ������.
	5����Ҫ�����๹�����е��ø��������Ĺ�������������ʾ����*/

public class Dept {
	private int deptId;
	private String deptName;
	//һ�Զ����õļ��ϣ���һ�����Ŷ�Ӧ���Ա��
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
