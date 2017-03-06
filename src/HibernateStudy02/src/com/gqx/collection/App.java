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
	 * Set集合
	 */
	@Test
	public void test() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		//**************保存************
		Set<String> addressSet=new HashSet<String>();
		addressSet.add("黄石");
		addressSet.add("荆州");
		User user=new User();
		user.setAddress(addressSet);
		user.setUserName("gqxing");
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}
	/**
	 * List集合
	 */
	@Test
	public void testSaveList() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		//**************保存************
		User user=new User();
		user.setUserName("gqxing");
		user.getAddressList().add("武汉");
		user.getAddressList().add("上海");
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * List集合
	 */
	@Test
	public void testSaveMap() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		//**************保存************
		User user=new User();
		user.setUserName("gqxing");
		user.getAddressMap().put("A002", "黄石");
		user.getAddressMap().put("A001", "荆州");
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * 获取 
	 */
	@Test
	public void testGet() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		//获取
		User user=(User)session.get(User.class, 2); //及时加载
		System.out.println(user.getUserId());
		System.out.println(user.getUserName());
		//当查询用户，可以获取与亲关联的list集合的数据（因为有正确的映射）
		//当遇到到集合数据的使用时，才向数据库发送执行的sql语句(懒加载）
		System.out.println(user.getAddressList());
		session.getTransaction().commit();
		session.close();
	}

}
