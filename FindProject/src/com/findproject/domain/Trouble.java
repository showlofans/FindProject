package com.findproject.domain;

public class Trouble {
	private int trouble_id;  	//问题id
	private int proj_id;
//	private NewDescription description;	//问题所依附的介绍内容
	private int trouble_flag;    	//问题编号
	private String trouble_info;	//问题信息
	private String trouble_time;  	//问题发布时间
	private String trouble_mail;
	public Trouble() {
		super();
	}
	
	public Trouble(int proj_id, int trouble_flag, String trouble_info,
			String trouble_time, String trouble_mail) {
		super();
		this.proj_id = proj_id;
		this.trouble_flag = trouble_flag;
		this.trouble_info = trouble_info;
		this.trouble_time = trouble_time;
		this.trouble_mail = trouble_mail;
	}

	public Trouble(int trouble_id, int proj_id, int trouble_flag,
			String trouble_info, String trouble_time, String trouble_mail) {
		super();
		this.trouble_id = trouble_id;
		this.proj_id = proj_id;
		this.trouble_flag = trouble_flag;
		this.trouble_info = trouble_info;
		this.trouble_time = trouble_time;
		this.trouble_mail = trouble_mail;
	}

	public Trouble(int trouble_id, int proj_id, int trouble_flag,
			String trouble_info, String trouble_time) {
		super();
		this.trouble_id = trouble_id;
		this.proj_id = proj_id;
		this.trouble_flag = trouble_flag;
		this.trouble_info = trouble_info;
		this.trouble_time = trouble_time;
	}

	public Trouble(int proj_id, int trouble_flag, String trouble_info,
			String trouble_time) {
		super();
		this.proj_id = proj_id;
		this.trouble_flag = trouble_flag;
		this.trouble_info = trouble_info;
		this.trouble_time = trouble_time;
	}

	//有flag
//	public Trouble(String _id, ProjectBean project, String trouble_flag,
//			String trouble_info, String trouble_time) {
//		super();
//		this._id = _id;
//		this.project = project;
//		this.trouble_flag = trouble_flag;
//		this.trouble_info = trouble_info;
//		this.trouble_time = trouble_time;
//	}
	//没有flag
//	public Trouble(String _id, ProjectBean project, String trouble_info,
//			String trouble_time) {
//		super();
//		this._id = _id;
//		this.project = project;
//		this.trouble_info = trouble_info;
//		this.trouble_time = trouble_time;
//	}

//	
//	public ProjectBean getProject() {
//		return project;
//	}
//	public void setProject(ProjectBean project) {
//		this.project = project;
//	}
	public String getTrouble_info() {
		return trouble_info;
	}
	public void setTrouble_info(String trouble_info) {
		this.trouble_info = trouble_info;
	}
	public String getTrouble_time() {
		return trouble_time;
	}
	public void setTrouble_time(String trouble_time) {
		this.trouble_time = trouble_time;
	}
	public int getTrouble_flag() {
		return trouble_flag;
	}
	public void setTrouble_flag(int trouble_flag) {
		this.trouble_flag = trouble_flag;
	}
	
	public int getProj_id() {
		return proj_id;
	}
	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}

	public int getTrouble_id() {
		return trouble_id;
	}

	public void setTrouble_id(int trouble_id) {
		this.trouble_id = trouble_id;
	}

	public String getTrouble_mail() {
		return trouble_mail;
	}

	public void setTrouble_mail(String trouble_mail) {
		this.trouble_mail = trouble_mail;
	}
	
}
