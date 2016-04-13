package com.stufinish.findproject.model2;

public class NewDescription {
	private int _id;				//介绍id
	private ProjectBean project; 	//新的介绍所依附的项目
	private String description_flag;	//介绍编号
	private String description_theme;		//介绍主题
	private String content_description;	//项目介绍
	private String description_time;	//介绍发布时间

	public NewDescription() {
		super();
		// TODO Auto-generated constructor stub
	}
	//有flag
	public NewDescription(int _id, ProjectBean project,
			String description_flag, String description_theme,
			String content_description, String description_time) {
		super();
		this._id = _id;
		this.project = project;
		this.description_flag = description_flag;
		this.description_theme = description_theme;
		this.content_description = content_description;
		this.description_time = description_time;
	}
	//没有flag
	public NewDescription(int _id, ProjectBean project,
			String description_theme, String content_description,
			String description_time) {
		super();
		this._id = _id;
		this.project = project;
		this.description_theme = description_theme;
		this.content_description = content_description;
		this.description_time = description_time;
	}


	public ProjectBean getProject() {
		return project;
	}
	public void setProject(ProjectBean project) {
		this.project = project;
	}
	public String getContent_description() {
		return content_description;
	}
	public void setContent_description(String content_description) {
		this.content_description = content_description;
	}
	public String getDescription_theme() {
		return description_theme;
	}
	public void setDescription_theme(String description_theme) {
		this.description_theme = description_theme;
	}
	public String getDescription_time() {
		return description_time;
	}
	public void setDescription_time(String description_time) {
		this.description_time = description_time;
	}
	public String getDescription_flag() {
		return description_flag;
	}
	public void setDescription_flag(String description_flag) {
		this.description_flag = description_flag;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}
	
}
