package com.peter.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.peter.bean.Order;
import com.peter.bean.Trade;
import com.peter.dao.BeanDao;
import com.peter.dao.BeanDaoImpl;
import com.peter.result.NetReturn;
import com.peter.result.Result;

@WebServlet("/PlaceOrder")
public class PlaceOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("������ʳɹ�");
		int userId = Integer.parseInt(request.getParameter("userId"));
		int tradeId = Integer.parseInt(request.getParameter("tradeId"));
		System.out.println("" + userId + ":" + tradeId);
		
		BeanDao dao = new BeanDaoImpl();
		Trade trade = dao.findById(Trade.class, tradeId);
		Order order = new Order();
		if (trade != null) {
			trade.setPayId(userId);
			dao.update(trade);
			order.setAuthorId(trade.getAuthorId());
			order.setCreateTime(System.currentTimeMillis());
			order.setTradeId(tradeId);
			order.setPayId(userId);
			dao.save(order);
		}
		
		Result<String> result = new Result<>();
		result.setCode(NetReturn.SUCCESS.code());
		result.setMsg(NetReturn.SUCCESS.msg());
		result.setData(null);
		String json = new Gson().toJson(result);
		System.out.println(json);
		
		response.setContentType("application/json; charset=utf-8");  
        response.setCharacterEncoding("UTF-8"); 
        OutputStream out = response.getOutputStream();  
        out.write(json.getBytes("UTF-8"));  
        out.flush(); 
	}
}