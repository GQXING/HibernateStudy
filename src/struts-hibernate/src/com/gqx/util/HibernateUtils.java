package com.gqx.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	//��ʼ��sessionFactory
	private static SessionFactory sf;
	static{
		sf=new Configuration().configure().buildSessionFactory();
	}
	//����session
	public static  Session getSession(){
		return sf.getCurrentSession();
	}
}

/**
 *  (1)��openSession �������Ͽ��Կ��ó������Ǵ�һ���µ�session���󣬶���ÿ��ʹ�ö��Ǵ�һ���µ�session��
 *  ��������ʹ�ö�Σ����õ�session����ͬһ�����󣬲���ʹ������Ҫ����close�����ر�session��
 *  
 *  (2)��getCurrentSession ��ȡ��ǰ������һ��session���󣬵���һ��ʹ�ô˷���ʱ�����Զ�����һ��session����
 *  ��������ʹ�ö��ʱ���õ���session����ͬһ�������������openSession������֮һ��
 *  �򵥶��ԣ�getCurrentSession ���ǣ�������Ѿ�ʹ�õģ��þɵģ����û�У����µġ�
 *  
 *  (3)������getCurrentSession ��˵��������һЩ�ص㣺
 *  	1�������ύ���Զ�close������Ҫ��openSessionһ���Լ�����close�����ر�session
 *  	2�����������ã�����hibernate.cfg.xml���У���Ҫ���ã�
 *  	<property name="current_session_context_class">thread</property>
 *  	��Ҫע�⣬�����current_session_context_class�����м�������ֵ��jta �� thread ���� , custom��managed ���� 
 *  	a).threadʹ��connection �����ݿ����ӹ�������
 * 		b).jta ��Java transaction api�� Java �ֲ�ʽ������� �������ݿ���ʣ���
 * 		c).jta ���м���ṩ��JBoss WebLogic �ȣ� ����tomcat ��֧�֣�
 */
