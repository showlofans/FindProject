<%@page import="com.findproject.add.AddProject"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	pageEncoding="utf-8"%>
<%@ page
	import="com.findproject.utils.TimeUtil,com.findproject.add.AddProject,com.findproject.domain.ProjectBean,java.util.*,java.io.*"%>

<%
	// project_type project_theme content_description  project_trouble 
	// project_resource project_link project_mind  project_renzhen
	request.setCharacterEncoding("utf-8");
	String proj_type = request.getParameter("proj_type").trim();
	String proj_theme = request.getParameter("proj_theme").trim();
	String proj_description = request.getParameter("proj_description").trim();
	String proj_link = request.getParameter("proj_link").trim();
	String proj_mind = request.getParameter("proj_mind").trim();
	String person_email = request.getParameter("person_email").trim();
	String proj_time = TimeUtil.getDate();
	ProjectBean bean = new ProjectBean( proj_link, proj_type, proj_mind,
			proj_theme, proj_description, proj_time,person_email);
	AddProject addProject = new AddProject();
	
	String result = addProject.add_project(bean);
	//判断返回结果
	if (result.equals("success")) {
		out.print("添加成功");
	} else if (result.equals("exist")) {
		out.print("exist");
	} else {
		out.print("error");
	}
%>