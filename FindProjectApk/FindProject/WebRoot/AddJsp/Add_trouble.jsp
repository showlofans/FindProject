<%@page import="com.findproject.dao.TroubleDao"%>
<%@ page contentType="text/html; charset=utf-8" language="java"
	pageEncoding="utf-8"%>
<%@ page
	import="com.findproject.utils.TimeUtil,com.findproject.add.*,com.findproject.domain.*,java.util.*,java.io.*"%>
<%	request.setCharacterEncoding("utf-8");
	String str_proj = request.getParameter("proj_id").trim();
	//String trouble_flag = request.getParameter("trouble_flag").trim();
	String trouble_content = request.getParameter("trouble_content").trim();
	String trouble_mail = request.getParameter("trouble_mail").trim();
	int proj_id = Integer.parseInt(str_proj);
	String trouble_time = TimeUtil.getDate();
	
	String[]array = trouble_content.split(",");
	//log(trouble_content);
	StringBuffer sb = new StringBuffer();
	TroubleDao dao = new TroubleDao();
	int m = dao.getCount(proj_id);
	String a = "";
	log(m+"");
	for(int i=0; i<array.length; i++){
		Trouble trouble = new Trouble(proj_id,1+m+i,array[i],trouble_time,trouble_mail);
		a = dao.addTrouble(trouble);
		log(array[i]);
	}
	//log(m+"");
	//判断返回结果
	if (a.equals("success")) {
		out.print("添加成功");
	} else {
		out.print("error");
	}
	
	log(str_proj);
	//trouble_content.getBytes("||");
	
	
	
	
	
	
%>