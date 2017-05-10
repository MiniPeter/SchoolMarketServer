package com.peter.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 * 例子：http://118.202.41.83:8080/SchoolMarketServer/LoginServlet?username=admin&password=admin
 */
@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("网络访问成功");
		String username = request.getParameter("username");
		username = new String(username.getBytes("iso-8859-1"), "UTF-8");

		String password = request.getParameter("password");
		password = new String(password.getBytes("iso-8859-1"), "UTF-8");

		System.out.println("username=" + username + ";password=" + password);
		
		String json="{\"code\":100,\"msg\":\"操作成功\",\"data\":{\"id\":\"U001\",\"username\":\"admin\",\"password\":" +
                "\"admin\",\"phone\":\"18202429136\",\"avatarUrl\":\"http://opeuvb611.bkt.clouddn.com/sort_clothes.jpeg\"}}";//"null"表示传值为String = "null";
		//Result<User> result = new Gson().fromJson(json,  new TypeToken<Result<User>>() {}.getType());
		  
		response.setContentType("application/json; charset=utf-8");  
        response.setCharacterEncoding("UTF-8"); 
        OutputStream out = response.getOutputStream();  
        out.write(json.getBytes("UTF-8"));  
        out.flush(); 
	}
}