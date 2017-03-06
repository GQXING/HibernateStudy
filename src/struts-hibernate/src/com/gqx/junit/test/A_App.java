package com.gqx.junit.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.gqx.entity.Dept;
import com.gqx.service.DeptService;
import com.gqx.util.HibernateUtils;

public class A_App {

	@Test
	public void test() {
		SessionFactory sf=new Configuration().configure().buildSessionFactory();
		Session session=sf.openSession();
		session.getTransaction();
		Dept dept=(Dept)session.get(Dept.class, 2);
		System.out.println(dept);
		session.getTransaction().commit();
		session.close();
		
	}

}
