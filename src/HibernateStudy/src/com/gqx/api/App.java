package com.gqx.api;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import com.gqx.demo1.User;

public class App {
	
	private static SessionFactory sf;
	static{
		//��ȡ���������ļ��Ĺ������͌�����
		Configuration config=new Configuration();
		sf=config.configure().addClass(User.class).buildSessionFactory();		//Ĭ�ϼ���src/hibernate.cfg.xml�ļ�
		
	}
	@Test
	public void test() {
		//�������ù��������
		Configuration config=new Configuration();
		//�����������ļ�
		config.configure();
		//�������������
		SchemaExport export=new SchemaExport(config);
		//����
		//��һ���������Ƿ��ڿ���̨��ӡsql���
		//�ڶ����������Ƿ�ִ�нű�
		export.create(true, true);
	}
	
	@Test
	public void test2() {
		Session session=sf.openSession();
		session.beginTransaction();	
		User user=(User)session.get(User.class, 2);
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void test3() {
		Session session=sf.openSession();
		session.beginTransaction();
		User user=(User)session.load(User.class, 1);
//		user.getName();\
		Hibernate.initialize(user);
		session.getTransaction().commit();
		session.close();
		System.out.println(user.getName());
	}

}
