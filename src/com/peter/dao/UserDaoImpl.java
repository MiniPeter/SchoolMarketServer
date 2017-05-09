package com.peter.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.peter.bean.User;
import com.peter.utils.HibernateUtil;

public class UserDaoImpl implements UserDao {

	// ����û�����Ҫ�������
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		// ����Sessionʵ��
        Session session = HibernateUtil.getSession();
        // ����Transactionʵ��
        Transaction tx = session.beginTransaction();
        try {
            // ʹ��Session��save�������־û����󱣴浽���ݿ�
            session.save(user);
            // �ύ����
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // �����쳣���ع�����
            tx.rollback();
        } finally {
            // �ر�Session����
            HibernateUtil.closeSession(session);
        }
	}

	@Override
    public User findById(String id) {
        User user = null;
        Session session = HibernateUtil.getSession();
        //ʹ��session��get������ȡָ��id���û�
        user = (User) session.get(User.class, id);
        if (user == null || "".equals(user)) {
            System.out.println("��ѯidΪ��" + id + "�޽��....");
        }
        HibernateUtil.closeSession(session);
        return user;
    }
	
	/*// ����id�����û� ,���Բ���Ҫ������� Get��ʽ
    @Override
    public User findByIdGet(int id) {
        User user = null;
        Session session = HibernateUtil.getSession();
        //ʹ��session��get������ȡָ��id���û�
        user = (User) session.get(User.class, id);
        if (user == null || "".equals(user)) {
            System.out.println("��ѯidΪ��" + id + "�޽��....");
        }
        HibernateUtil.closeSession(session);
        return user;
    }

    // ����id�����û� ,���Բ���Ҫ������� Load��ʽ
    @Override
    public User findByIdLoad(int id) {
        User user = null;
        Session session = HibernateUtil.getSession();
        // ʹ��session�ķ�����ȡָ��id���û�
        user = (User) session.load(User.class, id);
        if (user == null || "".equals(user)) {
            System.out.println("��ѯidΪ��" + id + "�޽��....");
        }
        HibernateUtil.closeSession(session);
        return user;
    }*/

	// ����HQl����ѯ
	@SuppressWarnings("unchecked")
    @Override
    public List<User> findByHQL(String hql) {
        List<User> list = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        list = session.createQuery(hql).list();
        HibernateUtil.closeSession(session);
        return list;
    }

	// ɾ���û� ,��Ҫ�������
    @Override
    public void delete(User user) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
        	HibernateUtil.closeSession(session);
        }
    }

    // �޸��û�
    @Override
    public void update(User user) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
        	HibernateUtil.closeSession(session);
        }
    }

}
