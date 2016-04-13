<%@ page contentType="text/html; charset=utf-8" import="com.findproject.dao.*,com.findproject.domain.*" language="java"%>
<%
  //此页面为由Android端填写注册信息，提交至服务器端的页面
  //  person_id,  contact_gets,  total_gets, 
  // person_name,   project_resource,   e_mail
  //注册
  //昵称，资源分享平台，邮箱
			 
  request.setCharacterEncoding("utf-8");
  String loginName = request.getParameter("LoginName").toString().trim();
  String loginPassword = request.getParameter("LoginPassword").toString().trim();
  String result = "";
  
  PersonDao dao = new PersonDao();
	 PersonBean bean = new PersonBean();
	String flag = dao.existPerson(loginName);
	if(flag.equals("exist")){
		//����������ݿ����ҵ���Ӧ���ǳƣ����Ƿ�ƥ��
		String get_name = dao.getPerson(loginName).getPerson_name();
		if (loginPassword.equals(get_name)) {
			session.setAttribute("user_name", loginName);
			out.print("success");		//��¼�ɹ�
			log("用户名:"+get_name+"登陆中....");
		} else {
			out.print("error");			//�ǳƣ����룩����
		}
	}else{
		out.print("nonexist");			//�û������䣩������
	}
	response.sendRedirect("index.jsp");
  
  
 // String project_resource = request.getParameter("project_resource").trim();
  
  
%>
