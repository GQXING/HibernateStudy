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
		//获取加载配置文件的管理类型對对象
		Configuration config=new Configuration();
		sf=config.configure().addClass(User.class).buildSessionFactory();		//默认加载src/hibernate.cfg.xml文件
		
	}
	@Test
	public void test() {
		//创建配置管理类对象
		Configuration config=new Configuration();
		//加载主配置文件
		config.configure();
		//创建工具类对象
		SchemaExport export=new SchemaExport(config);
		//建表
		//第一个参数：是否在控制台打印sql语句
		//第二个参数：是否执行脚本
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
