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

@WebServlet("/ConfirmTrade")
public class ConfirmTradeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("网络访问成功");
		int userId = Integer.parseInt(request.getParameter("userId"));
		int tradeId = Integer.parseInt(request.getParameter("tradeId"));
		System.out.println("" + userId + ":" + tradeId);
		
		BeanDao dao = new BeanDaoImpl();
		String hql = "FROM Order o WHERE o.tradeId = " + tradeId + "";
		List<Order> orders = dao.findByHQL(hql);
		if (!orders.isEmpty()) {
			Order order = orders.get(0);
			if (order.getAuthorId() == 0) {
				dao.delete(order);
				//还需要删除订单消息等
				Trade trade = dao.findById(Trade.class, tradeId);
				if (trade != null) {
					trade.setStatus(1);
					dao.update(trade);
				}
				
				hql = "FROM Msg m WHERE m.tradeId = " + tradeId + " AND "
						+ "m.authorId = " + userId + "";
				List<Msg> msgs = dao.findByHQL(hql);
				if (!msgs.isEmpty()) {
					dao.delete(msgs.get(0));
				}
				
			} else {
				order.setPayId(0);
				dao.update(order);
				hql = "FROM Msg m WHERE m.tradeId = " + tradeId + " AND "
						+ "m.authorId = " + userId + "";
				List<Msg> msgs = dao.findByHQL(hql);
				if (!msgs.isEmpty()) {
					dao.delete(msgs.get(0));
				}
				Trade trade = dao.findById(Trade.class, tradeId);
				if (trade != null) {
					trade.setStatus(2);
					dao.update(trade);
				}
			}
			
		}
		
		Result<String> result = new Result<>();
		result.setCode(NetReturn.SUCCESS.code());
		result.setMsg(NetReturn.SUCCESS.msg());
		result.setData("确认收货成功");
		String json = new Gson().toJson(result);
		System.out.println(json);
		
		response.setContentType("application/json; charset=utf-8");  
        response.setCharacterEncoding("UTF-8"); 
        OutputStream out = response.getOutputStream();  
        out.write(json.getBytes("UTF-8"));  
        out.flush(); 
		
	}
}