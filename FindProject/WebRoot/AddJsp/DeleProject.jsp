<%@ page contentType="text/html; charset=utf-8" language="java"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*,java.io.*"%>
<%@ page import="com.findproject.DB.ProjectDB"%>
<%
request.setCharacterEncoding("utf-8");
int proj_id = Integer.parseInt(request.getParameter("proj_id").trim());
ProjectDB db = new ProjectDB();
String str = db.delProject(proj_id);
out.print(str);
log(str);
%>