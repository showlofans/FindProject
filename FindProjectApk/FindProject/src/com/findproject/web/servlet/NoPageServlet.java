package com.findproject.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.findproject.dao.ProjectDAO;
import com.findproject.domain.ProjectBean;
import com.google.gson.Gson;

public class NoPageServlet extends HttpServlet {

	/**
	 * 响应Service2.activity
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		JSONObject js2 = new JSONObject();
		PrintWriter out = resp.getWriter();
		try {
			List<ProjectBean> list = new ArrayList<ProjectBean>();
			ProjectDAO dao = new ProjectDAO();
			int sizes = Integer.parseInt(req.getParameter("sizes").trim());
			int index = Integer.parseInt(req.getParameter("index").trim());
			String email = req.getParameter("email").trim();
			String type = req.getParameter("type").trim();
			log("" + type);
			log("" + sizes);
			log("" + index);
			JSONObject js = new JSONObject();
			int num = dao.getCount(email,type);
			list = dao.getAllItems(index, sizes, email, type);
			// js.addProperty("projlist", gson.toJson(list));
			Gson gson = new Gson();
			js2.put("isok", 0);
			js2.put("num", "" + num);
			js.put("projlist", new String(gson.toJson(list)));
			js2.put("data", js);
//			String str2 = js2.toString();
//			log(js.toString());
			log(js2.toString());
			log(js.toString());
			// log(new String(gson.toJson(list)));
			out.print(js2);
//			out.flush();
			out.close();

		} catch (Exception e) {
			try {
				js2.put("isok", 1);
				js2.put("data", "");
				String str2 = js2.toString();
				out.print(js2);
//				log("-----" + str2);
				out.flush();
				out.close();
			} catch (JSONException el) {
				System.out.println(el);
				e.printStackTrace();
			}
		}
	}

}
