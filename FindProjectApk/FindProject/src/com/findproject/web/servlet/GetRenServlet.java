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
//import com.findproject.dao.ProjectDAO;
//import com.findproject.domain.ProjectBean;
//import com.findproject.utils.TimeUtil;
//
//public class GetRenServlet extends HttpServlet {
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
//			doPost(request, response);
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
//		response.setContentType("text/html");
//		String proj_type = request.getParameter("proj_type").trim();
//		String proj_theme = request.getParameter("proj_theme").trim();
//		String proj_description = request.getParameter("proj_description").trim();
//		String proj_link = request.getParameter("proj_link").trim();
//		String proj_mind = request.getParameter("proj_mind").trim();
//		String proj_time = TimeUtil.getDate();
//		ProjectBean pbean = new ProjectBean(proj_type, proj_mind, proj_theme, proj_description, proj_time);
//		ProjectDAO dao = new ProjectDAO();
//		
//		Boolean flag = dao.exist_project(pbean);
//		PrintWriter out = response.getWriter();
//		if(flag){
//			out.
//		}else{
//			
//		}
//		
//		
//		out.wri
//		out.flush();
//		out.close();
//	}
//
//}
