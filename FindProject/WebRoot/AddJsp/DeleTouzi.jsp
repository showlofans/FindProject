<%@ page contentType="text/html; charset=utf-8" language="java"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*,java.io.*"%>
<%@ page import="com.findproject.DB.TouziDB"%>
<%
request.setCharacterEncoding("utf-8");
int touzi_id = Integer.parseInt(request.getParameter("touzi_id").trim());
log(touzi_id+"");
TouziDB db = new TouziDB();
String str = db.deleTouzi(touzi_id);
out.print(str);
log(str);
%>