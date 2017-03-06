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
	//1������ʹ�ö�������
	@Test
	public void test() {
		Session session1=sf.openSession();
		session1.beginTransaction();
		
		//a����ѯһ��
		Dept dept=(Dept)session1.get(Dept.class, 1);
		dept.getEmps().size();		//����
		session1.getTransaction().commit();
		session1.close();
		
		//�ڶ���session
		Session session2=sf.openSession();
		session2.beginTransaction();
		//������ѯ
		dept=(Dept)session2.get(Dept.class, 1);		//�����Ѿ����úã����ﲻ��Ҫ��ѯ���ݿ�
		dept.getEmps().size();		//����
		session2.getTransaction().commit();
		session2.close();
	}
	
	@Test
	public void testList() {
		Session session1=sf.openSession();
		session1.beginTransaction();
		//HQL��ѯ	**********setCacheable ָ���Ӷ��������д��ң����߷����������
		Query query=session1.createQuery("from Dept where deptId=1");
		System.out.println(query.list());
		session1.getTransaction().commit();
		session1.close();
		
		//�ڶ���session
		Session session2=sf.openSession();
		session2.beginTransaction();
		query=session2.createQuery("from Dept where deptId=1").setCacheable(true);
		System.out.println(query.list());	//����ѯ���ݿ⣬��Ҫ������ѯ����
		session2.getTransaction().commit();
		session2.close();
	}

}
