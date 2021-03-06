package com.peter.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.peter.bean.Msg;
import com.peter.bean.Notice;
import com.peter.bean.Order;
import com.peter.bean.Trade;
import com.peter.bean.User;
import com.peter.dao.BeanDao;
import com.peter.dao.BeanDaoImpl;
import com.peter.dao.UserDao;
import com.peter.dao.UserDaoImpl;
import com.peter.result.NetReturn;
import com.peter.result.Result;
import com.peter.utils.HibernateUtil;

public class HibernateTest {

	public static void main(String[] args) {
		//testUtil();//测试成功
		//testUserDao();//测试成功
		//testBeanDao();
		//loginTest();
		//drawerTest();
		testMsg();
	}
	
	public static void testMsg() {
		// 创建用户gg
		Msg msg = new Msg();
		msg.setAuthorId(3);
		Session session = HibernateUtil.getSession();
		Transaction Transaction = session.beginTransaction();
		try {
			session.save(msg);
			// 提交事务
			Transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 如果出现异常则进行事务回滚
			Transaction.rollback();
		}
	}
	
	public static void drawerTest() {
		String hql = "FROM Trade t WHERE t.status = 0 "
				+ "AND (t.payId = 1 OR (t.authorId = 1 AND t.payId != 0))";//待确认
		BeanDao dao = new BeanDaoImpl();
		List<Trade> trades = dao.findByHQL(hql);
		String json = new Gson().toJson(trades);
		System.out.println(json);
	}
	
	
	public static void loginTest() {
		String username = "admin";
		String password = "admin";
		BeanDao dao = new BeanDaoImpl();
		Result<User> result = new Result<>();
		String hql = "FROM User u WHERE u.username = '" + username + "'";
		List<User> users = dao.findByHQL(hql);
		if (users.isEmpty()) {
			result.setCode(NetReturn.USER_NOT_EXIST.code());
			result.setMsg(NetReturn.USER_NOT_EXIST.msg());
			result.setData(null);
		} else {
			User user = users.get(0);
			if (user.getPassword().equals(password)) {
				result.setCode(NetReturn.SUCCESS.code());
				result.setMsg(NetReturn.SUCCESS.msg());
				result.setData(user);
			} else {
				result.setCode(NetReturn.USER_PASS_ERR.code());
				result.setMsg(NetReturn.USER_PASS_ERR.msg());
				result.setData(null);
			}
		}
		String json = new Gson().toJson(result);
		System.out.println(json);
	}
	
	public static void testBeanDao() {
		User user = new User();
		user.setId(2);
		System.out.println("init:" + user.toString());
		BeanDao BeanDao = new BeanDaoImpl();
		
		BeanDao.save(user);
		
		User findUser = BeanDao.findById(User.class, user.getId());
		System.out.println("find:" + findUser.toString());
		
		List<User> list = null;
        // 1 String hql="FROM User";
        // 2 String hql="from User where type='admin'";
        String hql = "from User where id like '%U%'";
        list = BeanDao.findByHQL(hql);
        for (User u : list) {
            System.out.println("findHQL:" + u.toString());
        }
        
		user.setUsername("admin");
		BeanDao.update(user);
		System.out.println("update:" + BeanDao.findById(User.class, user.getId()));
		
		BeanDao.delete(user);
		System.out.println("delete:" + BeanDao.findById(User.class, user.getId()));
	}
	
	public static void testUserDao() {
		User user = new User();
		user.setId(2);
		System.out.println("init:" + user.toString());
		UserDao userDao = new UserDaoImpl();
		
		userDao.save(user);
		
		User findUser = userDao.findById(user.getId());
		System.out.println("find:" + findUser.toString());
		
		List<User> list = null;
        // 1 String hql="FROM User";
        // 2 String hql="from User where type='admin'";
        String hql = "from User where id like '%U%'";
        list = userDao.findByHQL(hql);
        for (User u : list) {
            System.out.println("findHQL:" + u.toString());
        }
        
		user.setUsername("admin");
		userDao.update(user);
		System.out.println("update:" + userDao.findById(user.getId()));
		
		userDao.delete(user);
		System.out.println("delete:" + userDao.findById(user.getId()));
		
	}

	public static void testUtil() {
		// 创建用户gg
		User user = new User();
		user.setId(1);
		User u = new User();
		u.setUsername("admin");
		User u2 = new User();
		u2.setUsername("admin");
		Notice notice = new Notice();
		notice.setId(1);
		Order order = new Order();
		order.setId(1);
		Trade trade = new Trade();
		trade.setId(1);
		
		Session session = HibernateUtil.getSession();

		// 进行事务处理
		Transaction Transaction = session.beginTransaction();
		try {

			// 对数据做保存至数据库
			session.save(user);
			session.save(u);
			session.save(u2);
			session.save(notice);
			session.save(order);
			session.save(trade);

			// 提交事务
			Transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 如果出现异常则进行事务回滚
			Transaction.rollback();
		}
	}
}
