package com.gqx.a_query;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
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
	public void test3() {
		Session session=sf.openSession();
		session.beginTransaction();
		System.out.println((Dept)session.get(Dept.class, 2));
		session.getTransaction().commit();
		session.close();

	}
	@Test
	public void test() {
		Session session=sf.openSession();
		session.beginTransaction();

		Query query=session.createQuery("from Employee");

		//从记录数
		ScrollableResults scroll=query.scroll();	//得到滚动的结果集
		scroll.last();								//滚到最后一条
		int totalCount=scroll.getRowNumber()+1;		//得到滚动的记录数，即总记录数

		//设置分页参数
		query.setFirstResult(0);
		query.setMaxResults(4);
		List<Employee> list=query.list();

		for (Employee employee : list) {
			System.out.println(employee);
		}

		session.getTransaction().commit();
		session.close();

	}

	@Test
	public void testHql() {
		Session session=sf.openSession();
		session.beginTransaction();
		//查询指定的列
		//		Query query=session.createQuery("select e.empId,e.empName from Employee e");


		//查询指定的列，同时自动封装【必须要提供带参数的构造器】
		//		Query query=session.createQuery("select new Employee(e.empId,e.empName) from Employee e");


		//条件查询  一个条件/多个条件/ or /and /like模糊查询
		//占位符
		//		Query query=session.createQuery("from Employee where empName=?");
		//		query.setString(0, "王五");
		//条件查询，命名参数
		//		Query query=session.createQuery("from Employee where empId=:myId and empName=:myName");
		//		query.setParameter("myId", 3);
		//		query.setParameter("myName", "王五");
		//模糊查询
		//		Query query=session.createQuery("from Employee where empName like ?");
		//		query.setString(0, "%王%");


		//聚合函数统计
		//		Query query=session.createQuery("select count(*) from Employee");
		//		Long num=(Long)query.uniqueResult();
		//		System.out.println(num);


		//分组查询
		Query query=session.createQuery("select e.dept,count(*) from Employee e group by e.dept");



		System.out.println(query.list());
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void testHqlInnerJoin() {
		Session session=sf.openSession();
		session.beginTransaction();
		//内连接【映射以及配置好了关系，关联的时候，直接写对象的属性即可】
		//Query query=session.createQuery("from Employee e inner join e.dept");


		//左外链接
		//Query query=session.createQuery("from Dept d left join d.emps");

		//左外链接
		//Query query=session.createQuery("from Employee e right join e.dept");
		
		//迫切内链接 （将又表的数据填充到左表）
		Query query=session.createQuery("from Employee e inner join fetch e.dept");
		
		
		query.list();
		//数组中第一个元素是Employee，第二个是Dept
		//		List<Object[]> list=query.list();
		//		for (int i = 0; i < list.size(); i++) {
		//			Object[] obj=list.get(0);
		//			Employee emp=(Employee)obj[0];
		//			Dept dept=(Dept)obj[1];
		//		}
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void testHql_Other() {
		Session session=sf.openSession();
		session.beginTransaction();
		//HQL写死
		//Query query=session.createQuery("from Dept d where deptId=:myId or deptName=:myName");
		//放到映射文件中去写
		Query query=session.getNamedQuery("getAllDept");
		query.setParameter(0, 2);
		System.out.println(query.list());
		
		session.getTransaction().commit();
		session.close();
	}
	
	//Criteria查询
	@Test
	public void testCriteria1() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		Criteria criteria=session.createCriteria(Employee.class);
		//构造条条
		//criteria.add(Restrictions.eq("empId", 2));条件多的话可以封装为一个map集合
		//根据主键查询
		criteria.add(Restrictions.idEq(2));
		
		System.out.println(criteria.list());
		session.getTransaction().commit();
		session.close();
	}
	
	//本地sql查询
	/*
	 * 不能夸数据库平台，如果改了数据库，sql语句可能有要改
	 */
	@Test
	public void testSQL() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		SQLQuery query=session.createSQLQuery("select * from t_Employee;").addEntity(Employee.class);
		
		List<Employee> list=query.list();
		for (Employee employee : list) {
			System.out.println(employee);
		} 
		System.out.println(query.list());
		session.getTransaction().commit();
		session.close();
	}
}
