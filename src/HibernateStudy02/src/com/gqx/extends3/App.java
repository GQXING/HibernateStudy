package com.gqx.extends3;

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
				.addClass(Animal.class)
				.buildSessionFactory();
	}
	@Test
	public void testOneTable() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		Cat cat=new Cat();
		cat.setName("��è");
		cat.setCatchMouse("ץС����");
		
		Monkey monkey=new Monkey();
		monkey.setName("������");
		monkey.setEatBanana("����ζ���㽶��");
		
		//����
		session.save(cat);
		session.save(monkey);
		session.getTransaction().commit();
		session.close();
	}

}
