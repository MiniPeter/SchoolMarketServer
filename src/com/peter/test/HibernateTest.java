package com.peter.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.peter.bean.Notice;
import com.peter.bean.Order;
import com.peter.bean.Trade;
import com.peter.bean.User;
import com.peter.utils.HibernateUtil;

public class HibernateTest {

	/*public static void main(String[] args) {
		test();
	}*/

	public static void test() {
		// 创建用户
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
