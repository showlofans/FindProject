<%@page import="com.findproject.utils.*"%>
<%@ page contentType="text/html; charset=utf-8" import="com.findproject.dao.*,com.findproject.domain.*,java.io.*,java.text.SimpleDateFormat,java.util.*" language="java"%>

<%
request.setCharacterEncoding("utf-8");
String picPath = request.getParameter("picPath").toString().trim();
String email = request.getParameter("e_mail").toString().trim();
PersonDao dao = new PersonDao();

SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
String time = df.format(new Date());// new Date()为获取当前系统时间

File2StringUtil.byte2Image(picPath, time);//存图片
String time1 = "image/" + time + ".jpg";

String str = dao.updateImg(email, time1);
out.print(str);
log(time1);
%>