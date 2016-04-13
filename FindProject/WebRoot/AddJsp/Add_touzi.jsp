<%@page import="com.findproject.utils.TimeUtil"%>
<%@page contentType="text/html; charset=utf-8" import="com.findproject.dao.*,com.findproject.domain.*" language="java"%>
<%	request.setCharacterEncoding("utf-8");
	String e_mail = request.getParameter("email").trim();
	int touzi_gets = Integer.parseInt(request.getParameter("touzi_gets").trim());
	String proj_id = request.getParameter("proj_id").trim();
	String time = TimeUtil.getDate();
	TouziBean touzi = new TouziBean(proj_id,touzi_gets,time,e_mail);
	TouziDao dao = new TouziDao();
	String str= dao.addTouzi(touzi);
	if(str.equals("success")){
		out.print("添加成功");
	}else{
		out.print("添加失败");
	}
%>
