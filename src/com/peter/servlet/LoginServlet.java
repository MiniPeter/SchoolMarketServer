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

/**
 * Servlet implementation class UserServlet
 * Àý×Ó£ºhttp://118.202.41.83:8080/SchoolMarketServer/LoginServlet?username=admin&password=admin
 */
@SuppressWarnings("serial")
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

	//getÂÒÂëString usernameString = new String(username.getBytes("ISO-8859-1"),"UTF-8");
    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		
	}

    //postÂÒÂë£ºrequest.setCharacterEncoding("UTF-8"):
    //responseÂÒÂë£ºresponse.setContentType("application/json; charset=utf-8");  
    //response.setCharacterEncoding("UTF-8"); 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ÍøÂç·ÃÎÊ³É¹¦");
		String username = request.getParameter("username");
		username = new String(username.getBytes("iso-8859-1"), "UTF-8");
		String password = request.getParameter("password");
		password = new String(password.getBytes("iso-8859-1"), "UTF-8");
		System.out.println("username=" + username + ";password=" + password);
		
		BeanDao dao = new BeanDaoImpl();
		Result<User> result = new Result<>();
		String hql = "FROM User u WHERE u.username = '" + username + "'";
		List<User> users = dao.findByHQL(hql);
		if (users.isEmpty()) {
			result.setCode(NetReturn.USER_NOT_EXIST.code());
			result.setMsg(NetReturn.USER_NOT_EXIST.msg());
			result.setData(null);
		} else {
			User user = users.get(0);
			if (user.getPassword().equals(password)) {
				result.setCode(NetReturn.SUCCESS.code());
				result.setMsg(NetReturn.SUCCESS.msg());
				result.setData(user);
			} else {
				result.setCode(NetReturn.USER_PASS_ERR.code());
				result.setMsg(NetReturn.USER_PASS_ERR.msg());
				result.setData(null);
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