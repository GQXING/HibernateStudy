package com.gqx.interceptor;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.gqx.util.HibernateUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * Session管理拦截器
 * 当访问action时，创建session
 * action --> service --> dao 获取的是这里创建的session
 * @author 郭庆兴
 *
 */
public class SessionInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		try {
			//1、先创建Session
			Session session=HibernateUtils.getSession();
			session.beginTransaction();
			//2、执行action
			String result=invocation.invoke();	//一旦执行到这一句就会打印出sql语句
			
			//4、提交事物
			session.getTransaction().commit();	//不需要关闭session
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

}
