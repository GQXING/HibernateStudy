package com.gqx.extends1;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Test;

import com.gqx.collection.User;

public class App {
	private static SessionFactory sf;
	static{
		sf=new Configuration().configure()
				.addClass(Cat.class)
				.buildSessionFactory();
	}
	@Test
	public void test() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		Cat cat=new Cat();
		cat.setName("��è");
		cat.setCatchMouse("ץС����");
		
		session.save(cat);
		
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void testGet() {
		Session session=sf.openSession();
		session.beginTransaction();
		
//		Query q=session.createQuery("from Cat");
//		List<Cat> list=q.list();
//		���ͨ�������ѯ����Ҫ˵����
		Query q=session.createQuery("from com.gqx.extends1.Cat");
		List<Animal> list=q.list();
		System.out.println(list);
		
		session.getTransaction().commit();
		session.close();
	}
	


}
