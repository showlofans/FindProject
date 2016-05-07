<%@page import="com.findproject.utils.TimeUtil"%>
<%@page contentType="text/html; charset=utf-8" import="com.findproject.dao.*,com.findproject.domain.*" language="java"%>
<%--<%	request.setCharacterEncoding("utf-8");
	String renzhen_info = request.getParameter("renzhen_info").trim();
	String proj_id = request.getParameter("proj_id").trim();
	String time = TimeUtil.getDate();
	RenZheng ren = new RenZheng(proj_id,renzhen_info,time);
	RenzhenDao dao = new RenzhenDao();
	String str = dao.add_renzhen(ren);
	if(str.equals("success")){
		out.print("添加成功");
	}else{
		out.print("添加失败");
	}
%>--%><%--
ProjectDAO pdao = new ProjectDAO();
	ProjectBean bean = pdao.getProject(proj_id);--%>