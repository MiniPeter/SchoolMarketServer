package com.peter.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.peter.utils.HibernateUtil;

public class BeanDaoImpl implements BeanDao {

	@Override
	public <T> void save(T t) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public <T> T findById(Class<T> clazz, String id) {
		// TODO Auto-generated method stub
		T t = null;
		Session session = HibernateUtil.getSession();
		t = (T) session.get(clazz, id);
		if (t == null || "".equals(t)) {
			System.out.println("查询id为：" + id + "无结果....");
		}
		HibernateUtil.closeSession(session);
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByHQL(String hql) {
		// TODO Auto-generated method stub
		List<T> list = new ArrayList<>();
		Session session = HibernateUtil.getSession();
		list = session.createQuery(hql).list();
		HibernateUtil.closeSession(session);
		return list;
	}

	@Override
	public <T> void delete(T t) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	@Override
	public <T> void update(T t) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
}
