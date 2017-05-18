package com.peter.servlet;

import java.io.IOException;
import java.io.OutputStream;

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
		Result<String> result = new Result<>();
		result.setCode(NetReturn.SUCCESS.code());
		result.setMsg(NetReturn.SUCCESS.msg());
		if (trade != null) {
			if (trade.getStatus() != 0) {
				result.setData("�������ˣ�����Ʒ�ѱ��µ���");
			} else {
				trade.setPayId(userId);
				trade.setStatus(4);
				dao.update(trade);
				order.setAuthorId(trade.getAuthorId());
				order.setCreateTime(System.currentTimeMillis());
				order.setTradeId(tradeId);
				order.setPayId(userId);
				dao.save(order);
				
				Msg msgBuy = new Msg();
				msgBuy.setTradeId(tradeId);
				msgBuy.setAuthorId(userId);
				msgBuy.setTitle("�µ���Ϣ");
				msgBuy.setContent("��ϲ���ɹ��µ���������ƷΪ��\"" + trade.getTitle() + "\"");
				msgBuy.setCreateTime(System.currentTimeMillis());
	            dao.save(msgBuy);
	            
	            Msg msgSold = new Msg();
	            msgSold.setTradeId(tradeId);
	            msgSold.setAuthorId(trade.getAuthorId());
	            msgSold.setTitle("������Ϣ");
				msgSold.setContent("��ϲ������Ʒ��\"" + trade.getTitle() 
					+ "\"" + "���µ���");
				msgSold.setCreateTime(System.currentTimeMillis());
				dao.save(msgSold);
				
				result.setData("�µ��ɹ���");
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