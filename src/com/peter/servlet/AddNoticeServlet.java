package com.peter.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
import com.peter.bean.Notice;
import com.peter.bean.Trade;
import com.peter.dao.BeanDao;
import com.peter.dao.BeanDaoImpl;
import com.peter.result.NetReturn;
import com.peter.result.Result;

@WebServlet("/AddNotice")
public class AddNoticeServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("�������ӳɹ�");
		
		Notice notice = new Notice();
		// �����ļ���Ŀ��������
		DiskFileItemFactory factory = new DiskFileItemFactory(); 
		// �����ļ��ϴ�·��
		File uploadDir = new File(this.getServletContext().getRealPath("/images/"));// �����ļ��ϴ���·��Ϊ��Ŀ��/upload/
		System.out.println("�ļ��ϴ���·��=" + uploadDir);
		if (!uploadDir.exists()) {// ������ļ��в����ھʹ���
			uploadDir.mkdirs();
		}
		// ��ȡϵͳĬ�ϵ���ʱ�ļ�����·������·��ΪTomcat��Ŀ¼�µ�temp�ļ���
		String temp = System.getProperty("java.io.tmpdir");
		// ���û�������СΪ 5M
		factory.setSizeThreshold(1024 * 1024 * 5);
		// ������ʱ�ļ���Ϊtemp
		factory.setRepository(new File(temp));
		// �ù���ʵ�����ϴ����,ServletFileUpload ���������ļ��ϴ�����
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);
			System.out.println("�ļ��ĸ���=" + list);
			String photoName = "";
			for (FileItem fileItem : list) {
				if (fileItem == null) {
					System.out.println("��ȡ�����ļ��ǿյ�");
					break;
				}
				if (fileItem.getFieldName().equals("notice")) {
	                String noticeJson = fileItem.getString(); 
	                System.out.println("String:" + noticeJson);
	                notice = new Gson().fromJson(noticeJson, 
	                		new TypeToken<Notice>(){}.getType());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\nNotice:" + notice.toString());
		BeanDao dao = new BeanDaoImpl();
		dao.save(notice);
		
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