package com.findproject.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.findproject.dao.ProjectDAO;
import com.findproject.dao.TroubleDao;
import com.findproject.domain.ProjectBean;
import com.findproject.domain.Trouble;
import com.google.gson.Gson;

public class GetTroubleServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		// try {
		PrintWriter out = response.getWriter();
		String ids = request.getParameter("proj_id").trim();
		int proj_id = Integer.parseInt(ids);
		Gson gson = new Gson();
		List<Trouble>troubles = new ArrayList<Trouble>();
		TroubleDao troubleDao = new TroubleDao();
		troubles = troubleDao.getTrouble(proj_id);
		String xString = gson.toJson(troubles);
		log(xString);
		out.write(new String(xString));
		out.flush();
		out.close();
		
	}

}
