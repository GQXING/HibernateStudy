package com.gqx.service;

import com.gqx.dao.deptDao.DeptDao;
import com.gqx.entity.Dept;

public class DeptService {
	//����dao�ķ���
	DeptDao dao=new DeptDao();
	public Dept findById(int id){
		return dao.findById(id);
	}
}
