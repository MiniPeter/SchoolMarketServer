package com.peter.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.peter.bean.User;
import com.peter.utils.HibernateUtil;

public class HibernateTest {

	/*public static void main(String[] args) {
		test();
	}*/

	public static void test() {
		// 创建用户
		User user = new User();
		user.setuId("U001");
		user.setuImgUrl("uImgUrl");
		user.setUsername("admin");
		user.setPassword("admin");

		Session session = HibernateUtil.getSession();

		// 进行事务处理
		Transaction Transaction = session.beginTransaction();
		try {

			// 对数据做保存至数据库
			session.save(user);

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
