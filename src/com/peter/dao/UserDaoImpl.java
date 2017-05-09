package com.peter.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.peter.bean.User;
import com.peter.utils.HibernateUtil;

public class UserDaoImpl implements UserDao {

	// 添加用户，需要事务管理
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		// 创建Session实例
        Session session = HibernateUtil.getSession();
        // 创建Transaction实例
        Transaction tx = session.beginTransaction();
        try {
            // 使用Session的save方法将持久化对象保存到数据库
            session.save(user);
            // 提交事务
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 出现异常，回滚事务
            tx.rollback();
        } finally {
            // 关闭Session连接
            HibernateUtil.closeSession(session);
        }
	}

	@Override
    public User findById(String id) {
        User user = null;
        Session session = HibernateUtil.getSession();
        //使用session的get方法获取指定id的用户
        user = (User) session.get(User.class, id);
        if (user == null || "".equals(user)) {
            System.out.println("查询id为：" + id + "无结果....");
        }
        HibernateUtil.closeSession(session);
        return user;
    }
	
	/*// 根据id查找用户 ,可以不需要事务管理 Get方式
    @Override
    public User findByIdGet(int id) {
        User user = null;
        Session session = HibernateUtil.getSession();
        //使用session的get方法获取指定id的用户
        user = (User) session.get(User.class, id);
        if (user == null || "".equals(user)) {
            System.out.println("查询id为：" + id + "无结果....");
        }
        HibernateUtil.closeSession(session);
        return user;
    }

    // 根据id查找用户 ,可以不需要事务管理 Load方式
    @Override
    public User findByIdLoad(int id) {
        User user = null;
        Session session = HibernateUtil.getSession();
        // 使用session的方法获取指定id的用户
        user = (User) session.load(User.class, id);
        if (user == null || "".equals(user)) {
            System.out.println("查询id为：" + id + "无结果....");
        }
        HibernateUtil.closeSession(session);
        return user;
    }*/

	// 根据HQl语句查询
	@SuppressWarnings("unchecked")
    @Override
    public List<User> findByHQL(String hql) {
        List<User> list = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        list = session.createQuery(hql).list();
        HibernateUtil.closeSession(session);
        return list;
    }

	// 删除用户 ,需要事务管理
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

    // 修改用户
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
