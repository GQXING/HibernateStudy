package com.gqx.component;

import static org.junit.Assert.*;

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
				.addClass(Car.class)
				.buildSessionFactory();
	}
	@Test
	public void test() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		//ÂÖ×Ó
		Wheel wheel=new Wheel();
		wheel.setCount(4);
		wheel.setSize(40);
		//Æû³µ
		Car car=new Car();
		car.setBrand("BMW");
		car.setWheel(wheel);
		//±£´æ
		session.save(car);
		session.getTransaction().commit();
		session.close();
	}

}
