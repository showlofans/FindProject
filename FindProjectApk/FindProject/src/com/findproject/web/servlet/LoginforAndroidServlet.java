package com.findproject.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.Flags.Flag;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Bean;

import com.findproject.dao.PersonDao;
import com.findproject.domain.PersonBean;

import java.lang.String;

public class LoginforAndroidServlet extends HttpServlet {

	/**
	 * Url:http://localhost:8080/FindProject/Servlet/LoginAnd
	 * ��¼servlet
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String loginName = request.getParameter("LoginName").trim();			//����	
		String loginPassword = request.getParameter("LoginPassword");	//�ǳ�
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			/**
			 * ��¼ҵ���ж�
			 */
			 PersonDao dao = new PersonDao();
			 PersonBean bean = new PersonBean();
			String flag = dao.existPerson(loginName);
			if(flag.equals("exist")){
				//����������ݿ����ҵ���Ӧ���ǳƣ����Ƿ�ƥ��
				String get_name = dao.getPerson(loginPassword).getPerson_name();
				if (loginName.equals(get_name)) {
					out.print("success");		//��¼�ɹ�
					log("用户名:"+get_name+"登陆中....");
				} else {
					out.print("error");			//�ǳƣ����룩����
				}
			}else{
				out.print("nonexist");			//�û������䣩������
			}
		} finally {
			out.flush();
			if (out != null) {
				out.close();// �����ַ����ڻ�������
			}

		}

	}

}
