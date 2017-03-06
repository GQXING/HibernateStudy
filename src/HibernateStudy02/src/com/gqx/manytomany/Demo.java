package com.gqx.manytomany;

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
				addClass(Developer.class).addClass(Project.class)
				.buildSessionFactory();
	}
	
	//只能通过一方维护另一方，不能重复维护
	//developer_zs.getProjects().add(project_oa);
	//但是在一对多或是多对一种，是候可以的
	@Test
	public void test() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		 //创建项目对象
		Project project_ds=new Project();
		project_ds.setPrj_name("电商系统");
		Project project_oa=new Project();
		project_oa.setPrj_name("OA系统");
		//创建员工对象
		Developer developer_zs=new Developer();
		developer_zs.setD_name("张三");
		Developer developer_ls=new Developer();
		developer_ls.setD_name("李四");
		Developer developer_ww=new Developer();
		developer_ww.setD_name("王五");
		//关系	通过项目方来保存
		project_ds.getDevelopers().add(developer_ww);
		project_ds.getDevelopers().add(developer_ls);
		project_oa.getDevelopers().add(developer_ls);
		project_oa.getDevelopers().add(developer_zs);
		
		
		session.save(project_oa);
		session.save(project_ds);
		session.save(developer_ww);
		session.save(developer_ls);
		session.save(developer_zs);
		
		session.getTransaction().commit();
		session.close();
		
	}
	
	//解除关系
	@Test
	public void testRemoveRelation() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		Project prj=(Project)session.get(Project.class, 1);
		prj.getDevelopers().clear();
		
		session.getTransaction().commit();
		session.close();
	}
	
	//删除
	/*
	 * inverse=true：有控制权，先删除中间表，在删除自身
	 * inverse=false：没有控制权。不能删除
	 */
	@Test
	public void testRemove() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		Project prj=(Project)session.get(Project.class, 2);
		session.delete(prj);
		
		session.getTransaction().commit();
		session.close();
	}
	
}
