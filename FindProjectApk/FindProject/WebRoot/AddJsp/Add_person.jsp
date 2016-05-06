<%@ page contentType="text/html; charset=utf-8" import="com.findproject.dao.*,com.findproject.domain.*" language="java"%>
<%
  //此页面为由Android端填写注册信息，提交至服务器端的页面
  //  person_id,  contact_gets,  total_gets, 
  // person_name,   project_resource,   e_mail
  //注册
  //昵称，资源分享平台，邮箱
			 
  request.setCharacterEncoding("utf-8");
  String person_name = request.getParameter("str_name").toString().trim();
  String project_resource = request.getParameter("str_res").toString().trim();
  String e_mail = request.getParameter("str_pass").toString().trim();
  String result = "";
  
  PersonDao dao = new PersonDao();
  String flag = dao.existPerson(e_mail);
  if(flag.equals("nonexist")){
	  PersonBean person = new PersonBean();
	  person.setPerson_name(person_name);
	  person.setE_mail(e_mail);
	  person.setProject_resource(project_resource);
	  result = dao.addper(person);
  }else{
	  result = flag;
  }
  //判断返回结果
  if(result.equals("success")){
	  out.print("success");
	  log(e_mail+"注册成功");
  }else if(result.equals("exist")){
	  out.print("exist");
  }else{
	  out.print("error");
  }
  
  
 // String project_resource = request.getParameter("project_resource").trim();
  
  
%>
