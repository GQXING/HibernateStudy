package com.gqx.api;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

public class Demo1 {
	private static SessionFactory sf;
	static{
		/*
		 * //1、创建配置管理对象	【Configuration：配置管理类对象】
		Configuration config=new Configuration();
		//加载配置文件（默认加载src/hibernate.cfg.xml)、
		config.configure();
		//2、根据加载的配置管理类对象，创建sessionFactory对象（代表了整个hibernate.cdg.xml配置文件）
		SessionFactory sf=config.buildSessionFactory();
		*/
		sf=new Configuration().configure().buildSessionFactory();
	}
	/*
	 * 保存对象
	 */
	@Test
	public void testInsert() {
		//对象
		User user=new User();
		user.setName("test");
		user.setPassword("666");
		//根据session工厂，创建session对象（维护了一个连接Connection，代表了数据库连接的会话）
		Session session=sf.openSession();
		//开启事物
		Transaction transaction=session.beginTransaction();
		/*************执行操作*********/
		session.save(user);
		//提交事物/关闭
		transaction.commit();
		session.close();
		sf.close();
	}
	
	/*
	 * 更新对象
	 */
	@Test
	public void testUpdate() {
		//对象
		User user=new User();
		user.setId(4);
		user.setName("test5");
		user.setPassword("666");
		//根据session工厂，创建session对象（维护了一个连接Connection，代表了数据库连接的会话）
		Session session=sf.openSession();
		//开启事物
		Transaction transaction=session.beginTransaction();
		/*************执行操作*********/
		//session.update(user);
		//主键查询
		//User user2=(User) session.get(User.class, 1);
		//User user2=(User) session.load(User.class, 1);	//同上
		//没有设置主键，执行保存操作（插入数据）；设置了主键，执行更新操作（若主键设置的值不存在也会报错）
		session.saveOrUpdate(user);
		//System.out.println(user2);
		//提交事物/关闭
		transaction.commit();
		session.close();
		sf.close();
	}
	
	
	//HQL查询 Hibernate query language
	@Test
	public void testHQL() {
		Session session=sf.openSession();
		//开启事物
		Transaction transaction=session.beginTransaction();
		
		//HQL查询，查询全部（注意：查询的是对象以及对象的属性，不是表，区分大小写）
		Query query=session.createQuery("from User where id=1 or id=2");
		List<User> users=query.list();
		System.out.println(users);
		transaction.commit();
		session.close();
		sf.close();
	}
	
	//QBC查询 query by Criteria
	@Test
	public void testQBC() {
		Session session=sf.openSession();
		//开启事物
		Transaction transaction=session.beginTransaction();
		
		Criteria criteria=session.createCriteria(User.class);
		//查询条件
		criteria.add(Restrictions.eq("id", 1));
		List<User> list=criteria.list();
		System.out.println(list);
		transaction.commit();
		session.close();
		sf.close();
	}
	
	//SQL查询
	@Test
	public void testSQL() {
		Session session=sf.openSession();
		//开启事物
		Transaction transaction=session.beginTransaction();
		//把每一行记录指定为的喜爱那个类型
		SQLQuery sqlQuery=session.createSQLQuery("select * from users").addEntity(User.class);
		List list=sqlQuery.list();
		System.out.println(list);
		transaction.commit();
		session.close();
		sf.close();
	}
	
	//分页查询
	@Test
	public void testPage() {
		Session session=sf.openSession();
		//开启事物
		Transaction transaction=session.beginTransaction();
		
		Query query=session.createQuery("from User");
		//设置分页参数  从0开始查询
		query.setFirstResult(0);	//查询的起始记录
		query.setMaxResults(4);		//查询的条数
		List<User> list=query.list();
		System.out.println(list);
		
		transaction.commit();
		session.close();
		sf.close();
	}
	
	
	
}
