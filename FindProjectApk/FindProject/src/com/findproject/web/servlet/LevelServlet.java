//package com.findproject.web.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.findproject.dao.LevelDao;
//import com.findproject.domain.LevelBean;
//public class LevelServlet extends HttpServlet {
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
//	/**
//	 * The doPost method of the servlet. <br>
//	 *
//	 * This method is called when a form has its tag value method equals to post.
//	 * 
//	 * @param request the request send by the client to the server
//	 * @param response the response send by the server to the client
//	 * @throws ServletException if an error occurred
//	 * @throws IOException if an error occurred
//	 */
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//		int proj_id = Integer.parseInt(request.getParameter("proj_id").toString().trim());
//		LevelDao dao = new LevelDao();
//		LevelBean levelBean = dao.getlevel(proj_id);
//		String str= dao.updateProject_order(levelBean);
//		PrintWriter out = response.getWriter();
//		if(str.equals("success")){
//			out.print(str);
//		}else{
//			out.print("null");
//		}
//		
//		
//		out.flush();
//		out.close();
//	}
//
//}
