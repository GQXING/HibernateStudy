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

		//通过部门方获取另外一方
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

		//通过员工方获取另外一方
		Employee employee=(Employee)session.get(Employee.class, 1);
		System.out.println(employee.getEmpName());
		System.out.println(employee.getDept().getDeptName());


		session.getTransaction().commit();
		session.close();

	}

	//解除关系
	@Test
	public void testRemoveRelation() {
		Session session=sf.openSession();
		session.beginTransaction();

		//获取部门
		Dept dept=(Dept)session.get(Dept.class, 2);
		//解除关系
		dept.getEmps().clear();

		session.getTransaction().commit();
		session.close();

	}

	//删除关联的数据
	/**
	 * inverse=false 有控制权，可以删除，先清除外键引用，再删除数据、
	 * inverse=true  没有控制权，不能删除
	 */
	@Test
	public void deleteData() {
		Session session=sf.openSession();
		session.beginTransaction();

		//获取部门
		Dept dept=(Dept)session.get(Dept.class, 2);
		session.delete(dept);

		session.getTransaction().commit();
		session.close();

	}


	
	/**
	 * 级联删除
	 */
	@Test
	public void delete() {
		Session session=sf.openSession();
		session.beginTransaction();

		//获取部门
		Dept dept=(Dept)session.get(Dept.class, 5);
		session.delete(dept);

		session.getTransaction().commit();
		session.close();

	}


}
