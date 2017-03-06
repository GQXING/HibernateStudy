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
	
	//ֻ��ͨ��һ��ά����һ���������ظ�ά��
	//developer_zs.getProjects().add(project_oa);
	//������һ�Զ���Ƕ��һ�֣��Ǻ���Ե�
	@Test
	public void test() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		 //������Ŀ����
		Project project_ds=new Project();
		project_ds.setPrj_name("����ϵͳ");
		Project project_oa=new Project();
		project_oa.setPrj_name("OAϵͳ");
		//����Ա������
		Developer developer_zs=new Developer();
		developer_zs.setD_name("����");
		Developer developer_ls=new Developer();
		developer_ls.setD_name("����");
		Developer developer_ww=new Developer();
		developer_ww.setD_name("����");
		//��ϵ	ͨ����Ŀ��������
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
	
	//�����ϵ
	@Test
	public void testRemoveRelation() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		Project prj=(Project)session.get(Project.class, 1);
		prj.getDevelopers().clear();
		
		session.getTransaction().commit();
		session.close();
	}
	
	//ɾ��
	/*
	 * inverse=true���п���Ȩ����ɾ���м����ɾ������
	 * inverse=false��û�п���Ȩ������ɾ��
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
