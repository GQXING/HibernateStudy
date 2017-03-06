package com.gqx.onetomany;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class DemoGet {
	private static SessionFactory sf;
	static{
		sf=new Configuration().
				configure().
				addClass(Dept.class).addClass(Employee.class)
				.buildSessionFactory();
	}
	@Test
	public void test() {
		Session session=sf.openSession();
		session.beginTransaction();

		//ͨ�����ŷ���ȡ����һ��
		Dept dept=(Dept)session.get(Dept.class, 1);
		System.out.println(dept.getDeptName());
		Set<Employee> set=dept.getEmps();
		Iterator<Employee> iterators=set.iterator();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			System.out.println(employee.getEmpName());
		}


		session.getTransaction().commit();
		session.close();

	}
	@Test
	public void test2() {
		Session session=sf.openSession();
		session.beginTransaction();

		//ͨ��Ա������ȡ����һ��
		Employee employee=(Employee)session.get(Employee.class, 1);
		System.out.println(employee.getEmpName());
		System.out.println(employee.getDept().getDeptName());


		session.getTransaction().commit();
		session.close();

	}

	//�����ϵ
	@Test
	public void testRemoveRelation() {
		Session session=sf.openSession();
		session.beginTransaction();

		//��ȡ����
		Dept dept=(Dept)session.get(Dept.class, 2);
		//�����ϵ
		dept.getEmps().clear();

		session.getTransaction().commit();
		session.close();

	}

	//ɾ������������
	/**
	 * inverse=false �п���Ȩ������ɾ���������������ã���ɾ�����ݡ�
	 * inverse=true  û�п���Ȩ������ɾ��
	 */
	@Test
	public void deleteData() {
		Session session=sf.openSession();
		session.beginTransaction();

		//��ȡ����
		Dept dept=(Dept)session.get(Dept.class, 2);
		session.delete(dept);

		session.getTransaction().commit();
		session.close();

	}


	
	/**
	 * ����ɾ��
	 */
	@Test
	public void delete() {
		Session session=sf.openSession();
		session.beginTransaction();

		//��ȡ����
		Dept dept=(Dept)session.get(Dept.class, 5);
		session.delete(dept);

		session.getTransaction().commit();
		session.close();

	}


}
