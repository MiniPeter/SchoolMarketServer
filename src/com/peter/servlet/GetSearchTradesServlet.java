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

@WebServlet("/GetSearchTrades")
public class GetSearchTradesServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Õ¯¬Á∑√Œ ≥…π¶");
		String query = request.getParameter("query");
		System.out.println(query);
		
		BeanDao dao = new BeanDaoImpl();
		String hql = "FROM Trade t WHERE t.status = 0 AND t.title like '%" + query + "%'";
		Result<List<Trade>> result = new Result<>();
		result.result(NetReturn.SUCCESS);
		List<Trade> trades = dao.findByHQL(hql);
		if (!trades.isEmpty()) {
			result.setData(trades);
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
