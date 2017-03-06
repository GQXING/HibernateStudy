package com.gqx.demo2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
public class test {
	private static SessionFactory sf;
	static{
		sf=new Configuration()
		.configure().addClass(Employee.class)
		.buildSessionFactory();
	}
	@Test
	public void test() {
		Session session=sf.openSession();
		Transaction transaction=session.beginTransaction();
		CompositeKeys keys=new CompositeKeys();
		keys.setName("test");
		keys.setAddress("ºþ±±»ÆÊ¯");

		Employee employee=new Employee();
		employee.setAge(21);
		employee.setKeys(keys);
		session.save(employee);

		transaction.commit();
		session.close();
		sf.close();
	}

}
