package com.peter.servlet;

import java.io.IOException;
import java.io.OutputStream;
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

@WebServlet("/CancelOrder")
public class CancelOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("网络访问成功");
		//int userId = Integer.parseInt(request.getParameter("userId"));
		int tradeId = Integer.parseInt(request.getParameter("tradeId"));
		System.out.println("" + tradeId);
		
		Result<String> result = new Result<>();
		result.setCode(NetReturn.SUCCESS.code());
		result.setMsg(NetReturn.SUCCESS.msg());
		
		BeanDao dao = new BeanDaoImpl();
		
		String hql = "FROM Order o WHERE o.tradeId = " + tradeId + "";
		List<Order> orders = dao.findByHQL(hql);
		if (!orders.isEmpty()) {
			Order order = orders.get(0);
			if (order.getAuthorId() == 0 || order.getPayId() == 0) {
				result.setData("完成线下交易后无法取消订单");
			} else {
				result.setData("取消订单成功");
				Trade trade = dao.findById(Trade.class, tradeId);
				if (trade != null) {
					if (trade.getPayId() != 0) {
						//取消订单操作
						trade.setPayId(0);
						dao.update(trade);
					
						dao.delete(order);
						
						hql = "FROM Msg m WHERE m.tradeId = " + tradeId + "";
						List<Msg> msgs = dao.findByHQL(hql);
						if (!msgs.isEmpty()) {
							for (Msg msg : msgs) {
								dao.delete(msg);
							}
						}
					}
				}
			}
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