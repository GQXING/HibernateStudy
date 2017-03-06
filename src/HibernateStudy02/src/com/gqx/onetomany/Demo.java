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
	 * 保存：部门方【一的一方】
	 */
	@Test
	public void test() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		//部门对象
		Dept dept=new Dept();
		dept.setDeptName("设计组");
		//员工对象
		Employee emp1=new Employee();
		emp1.setEmpName("张三2");
		Employee emp2=new Employee();
		emp2.setEmpName("李四2");
		//关系
		dept.getEmps().add(emp1);
		dept.getEmps().add(emp2);
		//保存
		session.save(dept);	//级联保存
//		session.save(emp1);		
//		session.save(emp2);
		
		
		session.getTransaction().commit();
		session.close();
		
		/* 结果
		 *Hibernate: insert into t_Employee (empName, salary, dept_id) values (?, ?, ?)
		 *Hibernate: insert into t_Employee (empName, salary, dept_id) values (?, ?, ?)
		 *Hibernate: insert into t_Dept (deptName) values (?)
		 *Hibernate: update t_Employee set deptId=? where empId=?
		 *Hibernate: update t_Employee set deptId=? where empId=?
		 *
		 *为什么会有五条sql语句？
		 *开始的时候先插入员工，并没有部门，一旦部门加入后，通过update的语句维护两者的关系
		 */
	}
	
	/**
	 * 保存：员工方【多的一方】
	 */
	@Test
	public void test2() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		//部门对象
		Dept dept=new Dept();
		dept.setDeptName("运维组");
		//员工对象
		Employee emp1=new Employee();
		emp1.setEmpName("王五");  
		Employee emp2=new Employee();
		emp2.setEmpName("老刘");
		//关系
		emp1.setDept(dept);
		emp2.setDept(dept);
		//保存
		session.save(emp1);
		session.save(emp2);
		session.save(dept);
		
		
		session.getTransaction().commit();
		session.close();
		
		/* 结果
		 *Hibernate: insert into t_Dept (deptName) values (?)
		 *Hibernate: insert into t_Employee (empName, salary, dept_id) values (?, ?, ?)
		 *Hibernate: insert into t_Employee (empName, salary, dept_id) values (?, ?, ?)
		 *
		 *如果将session.save(dept)放到两个员工的下方 结果为：
		 *	Hibernate: insert into t_Employee (empName, salary, dept_id) values (?, ?, ?)
			Hibernate: insert into t_Employee (empName, salary, dept_id) values (?, ?, ?)
			Hibernate: insert into t_Dept (deptName) values (?)
			Hibernate: update t_Employee set empName=?, salary=?, dept_id=? where empId=?
			Hibernate: update t_Employee set empName=?, salary=?, dept_id=? where empId=?
		 */
	}

}
