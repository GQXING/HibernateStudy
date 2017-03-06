package com.gqx.c_onetomany2;

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
		
		
		//�û�
		User user=new User();
		user.setUserName("gqxing");
		//���֤
		IdCard idCard=new IdCard();
		idCard.setCardNum("420281*****");
		idCard.setPlace("������ʯ");
		//��ϵ
		idCard.setUser(user);
		session.save(idCard);
		
		session.getTransaction().commit();
		session.close();
		
	}
	
}