package com.gqx.demo1;


import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App {
	private static SessionFactory sf;
	static{
		//获取加载配置文件的管理类型對对象
		Configuration config=new Configuration();
		sf=config.configure().addClass(User.class).buildSessionFactory();		//默认加载src/hibernate.cfg.xml文件
		
	}
	@Test
	public void test() {
		//对象
		User user=new User();
		user.setName("Hibernate");
		user.setPassword("111");

		//获取加载配置文件的管理类型對对象
		Configuration config=new Configuration();
		config.configure();		//默认加载src/hibernate.cfg.xml文件
		//创建session的工厂对象
		SessionFactory sf=config.buildSessionFactory();
		//创建session（session代表一个会话，与数据库连接的会话）
		Session session=sf.openSession();
		//开启事物
		Transaction tx=session.beginTransaction();
		//对象保存到数据库
		session.save(user);
		//提交事务
		tx.commit();
		//关闭
		session.close();
		sf.close();
	}

	@Test
	public void test2() {

		//获取加载配置文件的管理类型對对象
		Configuration config=new Configuration();
		config.configure();		//默认加载src/hibernate.cfg.xml文件
		//创建session的工厂对象
		SessionFactory sf=config.addClass(User.class).buildSessionFactory();
		//创建session（session代表一个会话，与数据库连接的会话）
		Session session=sf.openSession();
		//开启事物
		Transaction tx=session.beginTransaction();
		//对象保存到数据库
		User user=(User)session.get(User.class, 2);
		user.setName("郭大帅");
		//提交事务
		tx.commit();
		//关闭
		session.close();
		sf.close();
	}

	@Test
	public void flush() throws Exception {
		Configuration config=new Configuration();
		config.configure();		//默认加载src/hibernate.cfg.xml文件
		//创建session的工厂对象
		SessionFactory sf=config.addClass(User.class).buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		User user = null;
		user = (User) session.get(User.class, 5);
		user.setName("Jack");
		// 缓存数据与数据库同步
		session.flush();

		user.setName("Jack_new");

		session.getTransaction().commit();  // session.flush();
		session.close();
	}
	
	//1.  list 方法
		@Test
		public void list() throws Exception {
			Session session = sf.openSession();
			session.beginTransaction();
			// HQL查询
			Query q = session.createQuery("from User ");
			// list()方法
			List<User> list = q.list();
			
			for (int i=0; i<list.size(); i++){
				System.out.println(list.get(i));
			}
			
			session.getTransaction().commit();  
			session.close();
		}
		
		//2、iterator方法
		@Test
		public void iterator() throws Exception{
			Session session=sf.openSession();
			session.beginTransaction();
			//HQL查询
			Query query=session.createQuery("from User");
			//Iterator迭代
			Iterator<User> iterator=query.iterate();
			while (iterator.hasNext()) {
				User user = (User) iterator.next();
				System.out.println(user);
			}
			
			session.getTransaction().commit();
			session.close();
			
		}
		
		//3. 缓存
		@Test
		public void cache() throws Exception {
			Session session = sf.openSession();
			session.beginTransaction();
			
			/**************执行2次list*****************/
		/*	Query q = session.createQuery("from User");
			List<User> list = q.list();      // 【会放入？】
			for (int i=0; i<list.size(); i++){
				System.out.println(list.get(i));
			}
			System.out.println("=========list===========");
			list = q.list();				// 【会放入?】
			for (int i=0; i<list.size(); i++){
				System.out.println(list.get(i));
			}*/
			
			/**************执行2次iteator******************/
			Query q = session.createQuery("from User ");
			Iterator<User> it = q.iterate();		// 【放入缓存】
			while(it.hasNext()){
				User user = it.next();
				System.out.println(user);
			}
			System.out.println("==========iterate===========");
			it = q.iterate();						// 【也会从缓存中取】
			while(it.hasNext()){
				User user = it.next();
				System.out.println(user);
			}
			
			session.getTransaction().commit();  
			session.close();
		}
}
