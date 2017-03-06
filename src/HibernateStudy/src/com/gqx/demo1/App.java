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
		//��ȡ���������ļ��Ĺ������͌�����
		Configuration config=new Configuration();
		sf=config.configure().addClass(User.class).buildSessionFactory();		//Ĭ�ϼ���src/hibernate.cfg.xml�ļ�
		
	}
	@Test
	public void test() {
		//����
		User user=new User();
		user.setName("Hibernate");
		user.setPassword("111");

		//��ȡ���������ļ��Ĺ������͌�����
		Configuration config=new Configuration();
		config.configure();		//Ĭ�ϼ���src/hibernate.cfg.xml�ļ�
		//����session�Ĺ�������
		SessionFactory sf=config.buildSessionFactory();
		//����session��session����һ���Ự�������ݿ����ӵĻỰ��
		Session session=sf.openSession();
		//��������
		Transaction tx=session.beginTransaction();
		//���󱣴浽���ݿ�
		session.save(user);
		//�ύ����
		tx.commit();
		//�ر�
		session.close();
		sf.close();
	}

	@Test
	public void test2() {

		//��ȡ���������ļ��Ĺ������͌�����
		Configuration config=new Configuration();
		config.configure();		//Ĭ�ϼ���src/hibernate.cfg.xml�ļ�
		//����session�Ĺ�������
		SessionFactory sf=config.addClass(User.class).buildSessionFactory();
		//����session��session����һ���Ự�������ݿ����ӵĻỰ��
		Session session=sf.openSession();
		//��������
		Transaction tx=session.beginTransaction();
		//���󱣴浽���ݿ�
		User user=(User)session.get(User.class, 2);
		user.setName("����˧");
		//�ύ����
		tx.commit();
		//�ر�
		session.close();
		sf.close();
	}

	@Test
	public void flush() throws Exception {
		Configuration config=new Configuration();
		config.configure();		//Ĭ�ϼ���src/hibernate.cfg.xml�ļ�
		//����session�Ĺ�������
		SessionFactory sf=config.addClass(User.class).buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		User user = null;
		user = (User) session.get(User.class, 5);
		user.setName("Jack");
		// �������������ݿ�ͬ��
		session.flush();

		user.setName("Jack_new");

		session.getTransaction().commit();  // session.flush();
		session.close();
	}
	
	//1.  list ����
		@Test
		public void list() throws Exception {
			Session session = sf.openSession();
			session.beginTransaction();
			// HQL��ѯ
			Query q = session.createQuery("from User ");
			// list()����
			List<User> list = q.list();
			
			for (int i=0; i<list.size(); i++){
				System.out.println(list.get(i));
			}
			
			session.getTransaction().commit();  
			session.close();
		}
		
		//2��iterator����
		@Test
		public void iterator() throws Exception{
			Session session=sf.openSession();
			session.beginTransaction();
			//HQL��ѯ
			Query query=session.createQuery("from User");
			//Iterator����
			Iterator<User> iterator=query.iterate();
			while (iterator.hasNext()) {
				User user = (User) iterator.next();
				System.out.println(user);
			}
			
			session.getTransaction().commit();
			session.close();
			
		}
		
		//3. ����
		@Test
		public void cache() throws Exception {
			Session session = sf.openSession();
			session.beginTransaction();
			
			/**************ִ��2��list*****************/
		/*	Query q = session.createQuery("from User");
			List<User> list = q.list();      // ������룿��
			for (int i=0; i<list.size(); i++){
				System.out.println(list.get(i));
			}
			System.out.println("=========list===========");
			list = q.list();				// �������?��
			for (int i=0; i<list.size(); i++){
				System.out.println(list.get(i));
			}*/
			
			/**************ִ��2��iteator******************/
			Query q = session.createQuery("from User ");
			Iterator<User> it = q.iterate();		// �����뻺�桿
			while(it.hasNext()){
				User user = it.next();
				System.out.println(user);
			}
			System.out.println("==========iterate===========");
			it = q.iterate();						// ��Ҳ��ӻ�����ȡ��
			while(it.hasNext()){
				User user = it.next();
				System.out.println(user);
			}
			
			session.getTransaction().commit();  
			session.close();
		}
}
