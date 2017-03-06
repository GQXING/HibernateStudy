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
		 * //1���������ù������	��Configuration�����ù��������
		Configuration config=new Configuration();
		//���������ļ���Ĭ�ϼ���src/hibernate.cfg.xml)��
		config.configure();
		//2�����ݼ��ص����ù�������󣬴���sessionFactory���󣨴���������hibernate.cdg.xml�����ļ���
		SessionFactory sf=config.buildSessionFactory();
		*/
		sf=new Configuration().configure().buildSessionFactory();
	}
	/*
	 * �������
	 */
	@Test
	public void testInsert() {
		//����
		User user=new User();
		user.setName("test");
		user.setPassword("666");
		//����session����������session����ά����һ������Connection�����������ݿ����ӵĻỰ��
		Session session=sf.openSession();
		//��������
		Transaction transaction=session.beginTransaction();
		/*************ִ�в���*********/
		session.save(user);
		//�ύ����/�ر�
		transaction.commit();
		session.close();
		sf.close();
	}
	
	/*
	 * ���¶���
	 */
	@Test
	public void testUpdate() {
		//����
		User user=new User();
		user.setId(4);
		user.setName("test5");
		user.setPassword("666");
		//����session����������session����ά����һ������Connection�����������ݿ����ӵĻỰ��
		Session session=sf.openSession();
		//��������
		Transaction transaction=session.beginTransaction();
		/*************ִ�в���*********/
		//session.update(user);
		//������ѯ
		//User user2=(User) session.get(User.class, 1);
		//User user2=(User) session.load(User.class, 1);	//ͬ��
		//û������������ִ�б���������������ݣ���������������ִ�и��²��������������õ�ֵ������Ҳ�ᱨ��
		session.saveOrUpdate(user);
		//System.out.println(user2);
		//�ύ����/�ر�
		transaction.commit();
		session.close();
		sf.close();
	}
	
	
	//HQL��ѯ Hibernate query language
	@Test
	public void testHQL() {
		Session session=sf.openSession();
		//��������
		Transaction transaction=session.beginTransaction();
		
		//HQL��ѯ����ѯȫ����ע�⣺��ѯ���Ƕ����Լ���������ԣ����Ǳ����ִ�Сд��
		Query query=session.createQuery("from User where id=1 or id=2");
		List<User> users=query.list();
		System.out.println(users);
		transaction.commit();
		session.close();
		sf.close();
	}
	
	//QBC��ѯ query by Criteria
	@Test
	public void testQBC() {
		Session session=sf.openSession();
		//��������
		Transaction transaction=session.beginTransaction();
		
		Criteria criteria=session.createCriteria(User.class);
		//��ѯ����
		criteria.add(Restrictions.eq("id", 1));
		List<User> list=criteria.list();
		System.out.println(list);
		transaction.commit();
		session.close();
		sf.close();
	}
	
	//SQL��ѯ
	@Test
	public void testSQL() {
		Session session=sf.openSession();
		//��������
		Transaction transaction=session.beginTransaction();
		//��ÿһ�м�¼ָ��Ϊ��ϲ���Ǹ�����
		SQLQuery sqlQuery=session.createSQLQuery("select * from users").addEntity(User.class);
		List list=sqlQuery.list();
		System.out.println(list);
		transaction.commit();
		session.close();
		sf.close();
	}
	
	//��ҳ��ѯ
	@Test
	public void testPage() {
		Session session=sf.openSession();
		//��������
		Transaction transaction=session.beginTransaction();
		
		Query query=session.createQuery("from User");
		//���÷�ҳ����  ��0��ʼ��ѯ
		query.setFirstResult(0);	//��ѯ����ʼ��¼
		query.setMaxResults(4);		//��ѯ������
		List<User> list=query.list();
		System.out.println(list);
		
		transaction.commit();
		session.close();
		sf.close();
	}
	
	
	
}
