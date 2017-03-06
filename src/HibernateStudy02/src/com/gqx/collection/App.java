package com.gqx.collection;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Test;

public class App {
	private static SessionFactory sf;
	static{
		sf=new Configuration().configure().addClass(User.class).buildSessionFactory();
	}
	/**
	 * Set����
	 */
	@Test
	public void test() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		//**************����************
		Set<String> addressSet=new HashSet<String>();
		addressSet.add("��ʯ");
		addressSet.add("����");
		User user=new User();
		user.setAddress(addressSet);
		user.setUserName("gqxing");
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}
	/**
	 * List����
	 */
	@Test
	public void testSaveList() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		//**************����************
		User user=new User();
		user.setUserName("gqxing");
		user.getAddressList().add("�人");
		user.getAddressList().add("�Ϻ�");
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * List����
	 */
	@Test
	public void testSaveMap() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		//**************����************
		User user=new User();
		user.setUserName("gqxing");
		user.getAddressMap().put("A002", "��ʯ");
		user.getAddressMap().put("A001", "����");
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * ��ȡ 
	 */
	@Test
	public void testGet() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		//��ȡ
		User user=(User)session.get(User.class, 2); //��ʱ����
		System.out.println(user.getUserId());
		System.out.println(user.getUserName());
		//����ѯ�û������Ի�ȡ���׹�����list���ϵ����ݣ���Ϊ����ȷ��ӳ�䣩
		//���������������ݵ�ʹ��ʱ���������ݿⷢ��ִ�е�sql���(�����أ�
		System.out.println(user.getAddressList());
		session.getTransaction().commit();
		session.close();
	}

}
