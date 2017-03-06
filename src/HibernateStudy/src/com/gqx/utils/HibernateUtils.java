package com.gqx.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
	private static SessionFactory sf;
	static{
		//�����������ļ�,����SesssionFactory��
		sf=new Configuration().configure().buildSessionFactory();
	}
	//����session����  
	public static Session getSession(){
		return sf.openSession();
	}
	
}
