package com.stufinish.findproject.model2;

public class RenZheng {
	private String _id;		//认证id
	private ProjectBean project;//要认证的项目对象
	private String proj_id;
	private int renzhen_flag;	//认证编号
	private String renzhen_info;	//认证信息
	private String renzhen_time;	//添加认证时间
	
	
	public RenZheng() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RenZheng(int renzhen_flag, String renzhen_info, String renzhen_time) {
		super();
		this.renzhen_flag = renzhen_flag;
		this.renzhen_info = renzhen_info;
		this.renzhen_time = renzhen_time;
	}

	public RenZheng(ProjectBean project, String renzhen_info,
			String renzhen_time) {
		super();
		this.project = project;
		this.renzhen_info = renzhen_info;
		this.renzhen_time = renzhen_time;
	}

	public RenZheng(String proj_id, String renzhen_info, String renzhen_time) {
		super();
		this.proj_id = proj_id;
		this.renzhen_info = renzhen_info;
		this.renzhen_time = renzhen_time;
	}
	
	public RenZheng(String renzhen_info, String renzhen_time) {
		super();
		this.renzhen_info = renzhen_info;
		this.renzhen_time = renzhen_time;
	}

	public RenZheng(String proj_id, int renzhen_flag, String renzhen_info,
			String renzhen_time) {
		super();
		this.proj_id = proj_id;
		this.renzhen_flag = renzhen_flag;
		this.renzhen_info = renzhen_info;
		this.renzhen_time = renzhen_time;
	}

	//有flag
	public RenZheng(String _id, ProjectBean project, int renzhen_flag,
			String renzhen_info, String renzhen_time) {
		super();
		this._id = _id;
		this.project = project;
		this.renzhen_flag = renzhen_flag;
		this.renzhen_info = renzhen_info;
		this.renzhen_time = renzhen_time;
	}
	//没有flag
	public RenZheng(String _id, ProjectBean project, String renzhen_info,
			String renzhen_time) {
		super();
		this._id = _id;
		this.project = project;
		this.renzhen_info = renzhen_info;
		this.renzhen_time = renzhen_time;
	}
	public ProjectBean getProject() {
		return project;
	}
	public void setProject(ProjectBean project) {
		this.project = project;
	}
	public String getRenzhen_info() {
		return renzhen_info;
	}
	public void setRenzhen_info(String renzhen_info) {
		this.renzhen_info = renzhen_info;
	}
	public String getRenzhen_time() {
		return renzhen_time;
	}
	public void setRenzhen_time(String renzhen_time) {
		this.renzhen_time = renzhen_time;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public int getRenzhen_flag() {
		return renzhen_flag;
	}
	public void setRenzhen_flag(int renzhen_flag) {
		this.renzhen_flag = renzhen_flag;
	}
	public String getProj_id() {
		return proj_id;
	}
	public void setProj_id(String proj_id) {
		this.proj_id = proj_id;
	}
	
}
