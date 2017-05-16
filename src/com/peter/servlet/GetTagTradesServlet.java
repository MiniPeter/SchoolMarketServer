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

@WebServlet("/GetTagTrades")
public class GetTagTradesServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Õ¯¬Á∑√Œ ≥…π¶");
		String tagName = request.getParameter("tagName");
		tagName = new String(tagName.getBytes("iso-8859-1"), "UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("size"));
		System.out.println(tagName + ":" + page + ":" + size);
		
		int num = page * size;
		BeanDao dao = new BeanDaoImpl();
		Result<List<Trade>> result = new Result<>();
		result.result(NetReturn.SUCCESS);
		String hql = "FROM Trade t WHERE t.status = 0 AND t.tagName "
				+ "= '" + tagName + "'";
		List<Trade> trades = dao.findByHQL(hql);
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
