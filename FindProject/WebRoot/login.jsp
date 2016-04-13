<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'login.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<center>
		<br /> <br />
		<form action="login_do.jsp" method="post" name="denglu">
			<table>
				<tr>
					<td>注册邮箱</td><td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>用户名</td><td><input type="text" name="userpass"></td>
				</tr>
				<tr>
					<td ></td><td ></td><td><input type="button" name="username"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>
