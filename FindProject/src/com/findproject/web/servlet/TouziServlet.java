package com.findproject.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.findproject.dao.TouziDao;
import com.findproject.domain.TouziBean;
import com.google.gson.Gson;

public class TouziServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String email = request.getParameter("email").toString().trim();
		try{
			PrintWriter out = response.getWriter();
			log(email);
			ArrayList<TouziBean> list_touzi = new ArrayList<TouziBean>();
			TouziDao dao = new TouziDao();
			list_touzi = dao.getList(email);
			
			Gson gson = new Gson();
			String x = gson.toJson(list_touzi);
			out.write(new String(x));
			out.flush();
			out.close();
			
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
	}

}
