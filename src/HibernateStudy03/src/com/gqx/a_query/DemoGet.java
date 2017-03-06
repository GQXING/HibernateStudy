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

		//�Ӽ�¼��
		ScrollableResults scroll=query.scroll();	//�õ������Ľ����
		scroll.last();								//�������һ��
		int totalCount=scroll.getRowNumber()+1;		//�õ������ļ�¼�������ܼ�¼��

		//���÷�ҳ����
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
		//��ѯָ������
		//		Query query=session.createQuery("select e.empId,e.empName from Employee e");


		//��ѯָ�����У�ͬʱ�Զ���װ������Ҫ�ṩ�������Ĺ�������
		//		Query query=session.createQuery("select new Employee(e.empId,e.empName) from Employee e");


		//������ѯ  һ������/�������/ or /and /likeģ����ѯ
		//ռλ��
		//		Query query=session.createQuery("from Employee where empName=?");
		//		query.setString(0, "����");
		//������ѯ����������
		//		Query query=session.createQuery("from Employee where empId=:myId and empName=:myName");
		//		query.setParameter("myId", 3);
		//		query.setParameter("myName", "����");
		//ģ����ѯ
		//		Query query=session.createQuery("from Employee where empName like ?");
		//		query.setString(0, "%��%");


		//�ۺϺ���ͳ��
		//		Query query=session.createQuery("select count(*) from Employee");
		//		Long num=(Long)query.uniqueResult();
		//		System.out.println(num);


		//�����ѯ
		Query query=session.createQuery("select e.dept,count(*) from Employee e group by e.dept");



		System.out.println(query.list());
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void testHqlInnerJoin() {
		Session session=sf.openSession();
		session.beginTransaction();
		//�����ӡ�ӳ���Լ����ú��˹�ϵ��������ʱ��ֱ��д��������Լ��ɡ�
		//Query query=session.createQuery("from Employee e inner join e.dept");


		//��������
		//Query query=session.createQuery("from Dept d left join d.emps");

		//��������
		//Query query=session.createQuery("from Employee e right join e.dept");
		
		//���������� �����ֱ��������䵽���
		Query query=session.createQuery("from Employee e inner join fetch e.dept");
		
		
		query.list();
		//�����е�һ��Ԫ����Employee���ڶ�����Dept
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
		//HQLд��
		//Query query=session.createQuery("from Dept d where deptId=:myId or deptName=:myName");
		//�ŵ�ӳ���ļ���ȥд
		Query query=session.getNamedQuery("getAllDept");
		query.setParameter(0, 2);
		System.out.println(query.list());
		
		session.getTransaction().commit();
		session.close();
	}
	
	//Criteria��ѯ
	@Test
	public void testCriteria1() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		Criteria criteria=session.createCriteria(Employee.class);
		//��������
		//criteria.add(Restrictions.eq("empId", 2));������Ļ����Է�װΪһ��map����
		//����������ѯ
		criteria.add(Restrictions.idEq(2));
		
		System.out.println(criteria.list());
		session.getTransaction().commit();
		session.close();
	}
	
	//����sql��ѯ
	/*
	 * ���ܿ����ݿ�ƽ̨������������ݿ⣬sql��������Ҫ��
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
