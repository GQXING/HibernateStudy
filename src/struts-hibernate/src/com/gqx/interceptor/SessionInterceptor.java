package com.gqx.interceptor;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.gqx.util.HibernateUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * Session����������
 * ������actionʱ������session
 * action --> service --> dao ��ȡ�������ﴴ����session
 * @author ������
 *
 */
public class SessionInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		try {
			//1���ȴ���Session
			Session session=HibernateUtils.getSession();
			session.beginTransaction();
			//2��ִ��action
			String result=invocation.invoke();	//һ��ִ�е���һ��ͻ��ӡ��sql���
			
			//4���ύ����
			session.getTransaction().commit();	//����Ҫ�ر�session
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

}
