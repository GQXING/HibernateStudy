package com.gqx.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	//初始化sessionFactory
	private static SessionFactory sf;
	static{
		sf=new Configuration().configure().buildSessionFactory();
	}
	//创建session
	public static  Session getSession(){
		return sf.getCurrentSession();
	}
}

/**
 *  (1)、openSession 从字面上可以看得出来，是打开一个新的session对象，而且每次使用都是打开一个新的session，
 *  假如连续使用多次，则获得的session不是同一个对象，并且使用完需要调用close方法关闭session。
 *  
 *  (2)、getCurrentSession 获取当前上下文一个session对象，当第一次使用此方法时，会自动产生一个session对象，
 *  并且连续使用多次时，得到的session都是同一个对象，这就是与openSession的区别之一，
 *  简单而言，getCurrentSession 就是：如果有已经使用的，用旧的，如果没有，建新的。
 *  
 *  (3)、对于getCurrentSession 来说，有以下一些特点：
 *  	1、事务提交会自动close，不需要像openSession一样自己调用close方法关闭session
 *  	2、上下文配置（即在hibernate.cfg.xml）中，需要配置：
 *  	<property name="current_session_context_class">thread</property>
 *  	需要注意，这里的current_session_context_class属性有几个属性值：jta 、 thread 常用 , custom、managed 少用 
 *  	a).thread使用connection 单数据库连接管理事务
 * 		b).jta （Java transaction api） Java 分布式事务管理 （多数据库访问），
 * 		c).jta 由中间件提供（JBoss WebLogic 等， 但是tomcat 不支持）
 */
