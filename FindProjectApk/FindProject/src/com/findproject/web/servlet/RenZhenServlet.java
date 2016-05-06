//package com.findproject.web.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.findproject.dao.RenzhenDao;
//import com.findproject.domain.RenZheng;
//import com.google.gson.Gson;
//
//public class RenZhenServlet extends HttpServlet {
//
//	/**
//	 * The doGet method of the servlet. <br>
//	 *
//	 * This method is called when a form has its tag value method equals to get.
//	 * 
//	 * @param request the request send by the client to the server
//	 * @param response the response send by the server to the client
//	 * @throws ServletException if an error occurred
//	 * @throws IOException if an error occurred
//	 */
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doPost(request, response);
//	}
//
//	
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		response.setContentType("text/html");
//		String proj_id = request.getParameter("proj_id").toString().trim();
//		RenzhenDao dao = new RenzhenDao();
//		ArrayList<RenZheng> list = dao.get(proj_id);
//		Gson gson = new Gson();
//		String x = gson.toJson(list);
//		PrintWriter out = response.getWriter();
//		out.write(new String(x));
//		out.flush();
//		out.close();
//	}
//
//}
