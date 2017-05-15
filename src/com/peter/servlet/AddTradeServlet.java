package com.peter.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.peter.bean.Trade;
import com.peter.bean.User;
import com.peter.dao.BeanDao;
import com.peter.dao.BeanDaoImpl;
import com.peter.result.NetReturn;
import com.peter.result.Result;

//图片保存路径D:\\Programs\\workspaceJava\\SchoolMarketServer\\WebContent\\images\\
@WebServlet("/AddTrade")
public class AddTradeServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("网络连接成功");
		
		Trade trade = new Trade();
		// 创建文件项目工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory(); 
		// 设置文件上传路径
		//File uploadDir = new File(this.getServletContext().getRealPath("/upload/"));// 设置文件上传的路径为项目名/upload/
		File uploadDir = new File("D:\\Programs\\workspaceJava\\SchoolMarketServer\\WebContent\\images\\");
		System.out.println("文件上传的路径=" + uploadDir);
		if (!uploadDir.exists()) {// 如果改文件夹不存在就创建
			uploadDir.mkdirs();
		}
		// 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
		String temp = System.getProperty("java.io.tmpdir");
		// 设置缓冲区大小为 5M
		factory.setSizeThreshold(1024 * 1024 * 5);
		// 设置临时文件夹为temp
		factory.setRepository(new File(temp));
		// 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);
			System.out.println("文件的个数=" + list);
			String photoName = "";
			for (FileItem fileItem : list) {
				if (fileItem == null) {
					System.out.println("获取到的文件是空的");
					break;
				}
				
				if (fileItem.getFieldName().equals("trade")) {
	                String tradeJson = fileItem.getString(); 
	                System.out.println("String:" + tradeJson);
	                trade = new Gson().fromJson(tradeJson, 
	                		new TypeToken<Trade>(){}.getType());
	                int index = trade.getImgUrl().lastIndexOf("/");  
	                photoName = trade.getImgUrl().substring(index + 1);
				}
				
				if (fileItem.getFieldName().equals("photo")) {
					System.out.println("photoName:" + photoName);
					File file = new File(uploadDir, photoName);
					if (!file.exists()) {// 文件不存在才创建
						fileItem.write(file);// 保存文件
						System.out.println("文件名：" + file.getName());
					}
					trade.setImgUrl(file.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\nTrade:" + trade.toString());
		BeanDao dao = new BeanDaoImpl();
		dao.save(trade);
		
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
