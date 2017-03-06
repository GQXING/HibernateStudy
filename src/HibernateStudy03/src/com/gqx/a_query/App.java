package com.gqx.a_query;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Test;

public class App {

	private static SessionFactory sf;
	static{
		sf=new Configuration().
				configure().
				addClass(Dept.class).addClass(Employee.class)
				.buildSessionFactory();
	}
	//1、测试使用二级缓存
	@Test
	public void test() {
		Session session1=sf.openSession();
		session1.beginTransaction();
		
		//a、查询一次
		Dept dept=(Dept)session1.get(Dept.class, 1);
		dept.getEmps().size();		//集合
		session1.getTransaction().commit();
		session1.close();
		
		//第二个session
		Session session2=sf.openSession();
		session2.beginTransaction();
		//继续查询
		dept=(Dept)session2.get(Dept.class, 1);		//数据已经配置好，这里不需要查询数据库
		dept.getEmps().size();		//集合
		session2.getTransaction().commit();
		session2.close();
	}
	
	@Test
	public void testList() {
		Session session1=sf.openSession();
		session1.beginTransaction();
		//HQL查询	**********setCacheable 指定从二级缓存中存找，或者放入二级缓存
		Query query=session1.createQuery("from Dept where deptId=1");
		System.out.println(query.list());
		session1.getTransaction().commit();
		session1.close();
		
		//第二个session
		Session session2=sf.openSession();
		session2.beginTransaction();
		query=session2.createQuery("from Dept where deptId=1").setCacheable(true);
		System.out.println(query.list());	//不查询数据库，需要开启查询缓存
		session2.getTransaction().commit();
		session2.close();
	}

}
