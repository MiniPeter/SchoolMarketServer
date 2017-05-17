package com.peter.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.peter.bean.Msg;
import com.peter.bean.Order;
import com.peter.bean.Trade;
import com.peter.dao.BeanDao;
import com.peter.dao.BeanDaoImpl;
import com.peter.result.NetReturn;
import com.peter.result.Result;

@WebServlet("/GetMsgs")
public class GetMsgsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("网络访问成功");
		int userId = Integer.parseInt(request.getParameter("userId"));
		System.out.println("" + userId);
		
		BeanDao dao = new BeanDaoImpl();
		String hql = "FROM Order o WHERE o.authorId = " + userId + "";
		List<Order> orders = dao.findByHQL(hql);
		List<Msg> msgs = new ArrayList<>();
		Result<List<Msg>> result = new Result<>();
		result.setCode(NetReturn.SUCCESS.code());
		result.setMsg(NetReturn.SUCCESS.msg());
		if (!orders.isEmpty()) {
			for (Order order : orders) {
				dao.delete(order);
				Trade trade = dao.findById(Trade.class, order.getTradeId());
				Msg msg = new Msg();
				msg.setCreateTime(order.getCreateTime());
				msg.setTitle("订单消息");
				msg.setContent("恭喜您的商品：\"" + trade.getTitle() 
					+ "\"" + "被下单。");
				msgs.add(msg);
			}
			result.setData(msgs);
		} else {
			result.setData(null);
		}
		String json = new Gson().toJson(result);
		System.out.println(json);
		
		response.setContentType("application/json; charset=utf-8");  
        response.setCharacterEncoding("UTF-8"); 
        OutputStream out = response.getOutputStream();  
        out.write(json.getBytes("UTF-8"));  
        out.flush(); 
	}
}