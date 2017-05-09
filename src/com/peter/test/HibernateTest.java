package com.peter.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.peter.bean.Notice;
import com.peter.bean.Order;
import com.peter.bean.Trade;
import com.peter.bean.User;
import com.peter.dao.UserDao;
import com.peter.dao.UserDaoImpl;
import com.peter.utils.HibernateUtil;

public class HibernateTest {

	public static void main(String[] args) {
		//test();//测试成功
		//testUserDao();//测试成功
	}
	
	public static void testUserDao() {
		User user = new User();
		user.setId("U002");
		System.out.println("初始化" + user.toString());
		UserDao userDao = new UserDaoImpl();
		
		userDao.save(user);
		
		User findUser = userDao.findById(user.getId());
		System.out.println("find" + findUser.toString());
		
		List<User> list = null;
        // 1 String hql="FROM User";
        // 2 String hql="from User where type='admin'";
        String hql = "from User where id like '%U%'";
        list = userDao.findByHQL(hql);
        for (User u : list) {
            System.out.println("findHQL" + u.toString());
        }
        
		user.setUsername("admin");
		userDao.update(user);
		System.out.println("update" + userDao.findById(user.getId()));
		
		userDao.delete(user);
		System.out.println("delete" + userDao.findById(user.getId()));
		
	}

	public static void testUtil() {
		// 创建用户gg
		User user = new User();
		user.setId("U001");
		Notice notice = new Notice();
		notice.setId("N001");
		Order order = new Order();
		order.setId("O001");
		Trade trade = new Trade();
		trade.setId("T001");
		
		Session session = HibernateUtil.getSession();

		// 进行事务处理
		Transaction Transaction = session.beginTransaction();
		try {

			// 对数据做保存至数据库
			session.save(user);
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
