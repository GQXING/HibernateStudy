package com.gqx.action;

import com.gqx.entity.Dept;
import com.gqx.service.DeptService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeptAction extends ActionSupport{
	//逻辑业务类
	DeptService deptService=new DeptService();
	
	public String execute() throws Exception {
		Dept dept =deptService.findById(2);
		//保存
		ActionContext.getContext().put("dept", dept);
		return SUCCESS;
	}
}
