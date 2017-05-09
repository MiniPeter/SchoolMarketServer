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
		// �����û�
		User user = new User();
		user.setuId("U001");
		user.setuImgUrl("uImgUrl");
		user.setUsername("admin");
		user.setPassword("admin");

		Session session = HibernateUtil.getSession();

		// ����������
		Transaction Transaction = session.beginTransaction();
		try {

			// �����������������ݿ�
			session.save(user);

			// �ύ����
			Transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// ��������쳣���������ع�
			Transaction.rollback();
		}
	}
}
