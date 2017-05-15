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
import com.peter.bean.User;
import com.peter.dao.BeanDao;
import com.peter.dao.BeanDaoImpl;
import com.peter.result.NetReturn;
import com.peter.result.Result;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Õ¯¬Á∑√Œ ≥…π¶");
		String username = request.getParameter("username");
		username = new String(username.getBytes("iso-8859-1"), "UTF-8");
		String password = request.getParameter("password");
		password = new String(password.getBytes("iso-8859-1"), "UTF-8");
		System.out.println("username=" + username + ";password=" + password);
		
		BeanDao dao = new BeanDaoImpl();
		Result<String> result = new Result<>();
		String hql = "FROM User u WHERE u.username = '" + username + "'";
		List<User> users = dao.findByHQL(hql);
		if (users.isEmpty()) {
			result.setCode(NetReturn.SUCCESS.code());
			result.setMsg(NetReturn.SUCCESS.msg());
			result.setData(null);
		} else {
			result.setCode(NetReturn.USER_NAME_EXIST.code());
			result.setMsg(NetReturn.USER_NAME_EXIST.msg());
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
