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
		// �����û�
		User user = new User();
		user.setId("U001");
		Notice notice = new Notice();
		notice.setId("N001");
		Order order = new Order();
		order.setId("O001");
		Trade trade = new Trade();
		trade.setId("T001");
		
		Session session = HibernateUtil.getSession();

		// ����������
		Transaction Transaction = session.beginTransaction();
		try {

			// �����������������ݿ�
			session.save(user);
			session.save(notice);
			session.save(order);
			session.save(trade);

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
