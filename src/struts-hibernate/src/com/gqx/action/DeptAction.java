package com.gqx.action;

import com.gqx.entity.Dept;
import com.gqx.service.DeptService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeptAction extends ActionSupport{
	//�߼�ҵ����
	DeptService deptService=new DeptService();
	
	public String execute() throws Exception {
		Dept dept =deptService.findById(2);
		//����
		ActionContext.getContext().put("dept", dept);
		return SUCCESS;
	}
}
