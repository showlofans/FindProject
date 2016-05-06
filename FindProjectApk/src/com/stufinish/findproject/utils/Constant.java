package com.stufinish.findproject.utils;

public class Constant {// 100.73.80.118 192.168.43.225 192.168.56.1
	public static final String aURL = "http://192.168.43.225:8080/FindProject";
	public static final String addperUrl = aURL
			+ "/web/servlet/AddPersonServlet";

	public static final String registerUrl = aURL
			+ "/web/servlet/PersonServlet";
	public static final String levelUrl = aURL + "/web/servlet/LevelServlet";

	public static final String projectUrl = aURL + "/servlet/ProjectServlet";
	public static final String myProjectUrl = aURL
			+ "/servlet/MyProjectServlet";
	public static final String servletUrl = aURL + "/servlet/NoPageServlet";
	// public static final String typeUrl=aURL+"/servlet/TypeServlet";
	public static final String AddProjectUrl = aURL + "/AddJsp/Add_project.jsp";
	public static final String AddRenzhentUrl = aURL
			+ "/AddJsp/Add_renzhen.jsp";
	public static final String loginUrl = aURL + "/servlet/LoginAnd";
	public static final String renzhenUrl = aURL + "/servlet/RenZhenServlet";
	public static final String touziGetsUrl = aURL + "/AddJsp/Add_touzi.jsp";
	public static final String touziUrl = aURL + "/servlet/TouziServlet";
	public static final String getRenResult = aURL + "/AddJsp/Add_touzi.jsp";

	public static final String addTrouble = aURL + "/AddJsp/Add_trouble.jsp";
	public static final String getTrouble = aURL + "/servlet/GetTroubleServlet";
	public static final String addPersonJ = aURL + "/AddJsp/Add_person.jsp";
	public static final String imgUrl = aURL + "/AddJsp/Update_image.jsp";
	// 根据项目id，删除项目
	public static final String deleProject = aURL + "/AddJsp/DeleProject.jsp";

	public static final int PerseonActivity = 9;
	public static final int USERINFO = 10;
	public static final int loginForegister = 100;
	// public static final String cacheUrl=getCacheDir().getPath()+"/";
	// public static final String renzhenUrl=aURL+"/servlet/RenZhenServlet";
}
