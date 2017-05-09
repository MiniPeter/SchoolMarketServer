package com.peter.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private HibernateUtil() {

	};

	public static SessionFactory SessionFactory = null;

	static {
		// hibernate初始化
		Configuration cf = new Configuration();
		cf.configure();
		SessionFactory = cf.buildSessionFactory();// DriverManager.getconnection()
		// Session session = SessionFactory.openSession();//相当于得到Connection对象
	}

	public static Session getSession() {

		return SessionFactory.openSession();
	}

	public static void closeSession(Session session) {
		if (session != null) {
			session.clear();
		}
	}
}