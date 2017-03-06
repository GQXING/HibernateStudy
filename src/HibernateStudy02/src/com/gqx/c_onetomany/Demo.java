package com.gqx.c_onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class Demo {
	private static SessionFactory sf;
	static{
		sf=new Configuration().
				configure().
				addClass(User.class).addClass(IdCard.class)
				.buildSessionFactory();
	}
	
	@Test
	public void test() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		
		//用户
		User user=new User();
		user.setUserName("gqxing");
		//身份证
		IdCard idCard=new IdCard();
		idCard.setCardNum("420281*****");
		idCard.setPlace("湖北黄石");
		//关系
		session.save(user);
		session.save(idCard);
		
		session.getTransaction().commit();
		session.close();
		
	}
	
}