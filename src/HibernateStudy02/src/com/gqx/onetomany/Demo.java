package com.gqx.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class Demo {
	private static SessionFactory sf;
	static{
		sf=new Configuration().
				configure().
				addClass(Dept.class).addClass(Employee.class)
				.buildSessionFactory();
	}
	/**
	 * ���棺���ŷ���һ��һ����
	 */
	@Test
	public void test() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		//���Ŷ���
		Dept dept=new Dept();
		dept.setDeptName("�����");
		//Ա������
		Employee emp1=new Employee();
		emp1.setEmpName("����2");
		Employee emp2=new Employee();
		emp2.setEmpName("����2");
		//��ϵ
		dept.getEmps().add(emp1);
		dept.getEmps().add(emp2);
		//����
		session.save(dept);	//��������
//		session.save(emp1);		
//		session.save(emp2);
		
		
		session.getTransaction().commit();
		session.close();
		
		/* ���
		 *Hibernate: insert into t_Employee (empName, salary, dept_id) values (?, ?, ?)
		 *Hibernate: insert into t_Employee (empName, salary, dept_id) values (?, ?, ?)
		 *Hibernate: insert into t_Dept (deptName) values (?)
		 *Hibernate: update t_Employee set deptId=? where empId=?
		 *Hibernate: update t_Employee set deptId=? where empId=?
		 *
		 *Ϊʲô��������sql��䣿
		 *��ʼ��ʱ���Ȳ���Ա������û�в��ţ�һ�����ż����ͨ��update�����ά�����ߵĹ�ϵ
		 */
	}
	
	/**
	 * ���棺Ա���������һ����
	 */
	@Test
	public void test2() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		//���Ŷ���
		Dept dept=new Dept();
		dept.setDeptName("��ά��");
		//Ա������
		Employee emp1=new Employee();
		emp1.setEmpName("����");  
		Employee emp2=new Employee();
		emp2.setEmpName("����");
		//��ϵ
		emp1.setDept(dept);
		emp2.setDept(dept);
		//����
		session.save(emp1);
		session.save(emp2);
		session.save(dept);
		
		
		session.getTransaction().commit();
		session.close();
		
		/* ���
		 *Hibernate: insert into t_Dept (deptName) values (?)
		 *Hibernate: insert into t_Employee (empName, salary, dept_id) values (?, ?, ?)
		 *Hibernate: insert into t_Employee (empName, salary, dept_id) values (?, ?, ?)
		 *
		 *�����session.save(dept)�ŵ�����Ա�����·� ���Ϊ��
		 *	Hibernate: insert into t_Employee (empName, salary, dept_id) values (?, ?, ?)
			Hibernate: insert into t_Employee (empName, salary, dept_id) values (?, ?, ?)
			Hibernate: insert into t_Dept (deptName) values (?)
			Hibernate: update t_Employee set empName=?, salary=?, dept_id=? where empId=?
			Hibernate: update t_Employee set empName=?, salary=?, dept_id=? where empId=?
		 */
	}

}
