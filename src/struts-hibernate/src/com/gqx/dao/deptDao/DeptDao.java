package com.gqx.dao.deptDao;

import com.gqx.entity.Dept;
import com.gqx.util.HibernateUtils;

public class DeptDao {
	//Ö÷¼ü²éÑ¯
	public Dept findById(int id){
		return (Dept)HibernateUtils.getSession().get(Dept.class, id);
	}
}
