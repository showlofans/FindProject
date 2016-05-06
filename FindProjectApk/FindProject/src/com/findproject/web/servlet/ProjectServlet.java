//package com.findproject.web.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.findproject.dao.LevelDao;
//import com.findproject.dao.ProjectDAO;
//import com.findproject.domain.DevidePage;
//import com.findproject.domain.LevelBean;
//import com.findproject.domain.PersonBean;
//import com.findproject.domain.ProjectBean;
//import com.google.gson.Gson;
//
//public class ProjectServlet extends HttpServlet {
//
//	/**
//	 * 
//	 */
//	//
//	// public ProjectServlet(){
//	// super();
//	// }
//	// public void destroy(){
//	// super.destroy();
//	// }
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//		try {
//			PrintWriter out = response.getWriter();
//			List<ProjectBean> list = new ArrayList<ProjectBean>();
//			// List<LevelBean> levelList = new ArrayList<LevelBean>();
//			String email = request.getParameter("email").trim();
//			ProjectDAO dao = new ProjectDAO();
//			list = dao.find(email);
////			// 分页：每页有25条数据，因为有100条，所以总共有4页。
//			
////			String pageNo = request.getParameter("pageNo");
////			int currentPage=0;// 当前页是第一页
////			if (pageNo != null) {
////				currentPage = Integer.parseInt(pageNo);
////			}
//			Gson gson = new Gson();
//			//10为每页加载的数量
////			int i = dao.getCount() / 10;
////			if (currentPage >= i) {
////				out.write("finish");
////			} else {
////				DevidePage pUtil = new DevidePage(10, list.size(), currentPage);
////				int start = pUtil.getFromIndex();
////				int end = pUtil.getToIndex();
////				List<ProjectBean> subList = list.subList(start, end);
//
//				// levelList = dao.getLevelList();
//
//				// String x = gson.toJson(map);
//				// String y = gson.toJson(levelList);
//				String z = gson.toJson(list);
//				out.write(new String(z));
////				log("第" + pageNo + "页");
////			}
//			out.flush();
//			out.close();
//
//		} catch (Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
//		}
//
//	}
//
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//}