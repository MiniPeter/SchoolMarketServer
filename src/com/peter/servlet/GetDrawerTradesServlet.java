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
import com.peter.bean.Trade;
import com.peter.dao.BeanDao;
import com.peter.dao.BeanDaoImpl;
import com.peter.result.NetReturn;
import com.peter.result.Result;

@WebServlet("/GetDrawerTrades")
public class GetDrawerTradesServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("网络访问成功");
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		int page = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("size"));
		int myId = Integer.parseInt(request.getParameter("myId"));
		System.out.println("" + typeId + ":" + page + ":" + size);
		
        String hql = "";
		switch (typeId) {
			case 0 : hql = "FROM Trade t WHERE t.status != 1 "
					+ "AND (t.payId = " + myId + " OR (t.authorId = " + myId + " AND t.payId != 0))";//待确认
				break;
			case 1 : hql = "FROM Trade t WHERE t.status = 1 AND t.payId = " + myId + "";//已买
				break;
			case 2 : hql = "FROM Trade t WHERE t.status != 1 AND t.authorId = " + myId + "";//正在卖
				break;
			case 3 : hql = "FROM Trade t WHERE t.status = 1 AND t.authorId = " + myId + "";//已卖
				break;
		}
		BeanDao dao = new BeanDaoImpl();
		Result<List<Trade>> result = new Result<>();
		result.result(NetReturn.SUCCESS);
		List<Trade> trades = dao.findByHQL(hql);
		int num = page * size;
		if (trades.size() <= num) {
			result.setData(trades);
		} else {
			List<Trade> tems = new ArrayList<>();
			for (int i = 0; i < num; i++) {
				tems.add(trades.get(i));
			}
			result.setData(tems);
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