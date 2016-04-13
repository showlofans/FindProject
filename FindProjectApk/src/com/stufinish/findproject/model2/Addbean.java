package com.stufinish.findproject.model2;


public class Addbean {

	/**
	 * 
	 */
	private int proj_id;
	private String persoName;
	private String level_type;
	private String content_mind;
	
	
	private String project_time;		//项目发布时间
	private String content_description;	//项目介绍
	private String project_theme;		//项目主题
	
	public Addbean(int proj_id,String persoName, String level_type, String content_mind,
			String project_time, String content_description,
			String project_theme) {
		super();
		this.proj_id = proj_id;
		this.persoName = persoName;
		this.level_type = level_type;
		this.content_mind = content_mind;
		this.project_time = project_time;
		this.content_description = content_description;
		this.project_theme = project_theme;
	}
	
	public Addbean() {
		// TODO Auto-generated constructor stub
	}

	public String getPersoName() {
		return persoName;
	}
	public void setPersoName(String persoName) {
		this.persoName = persoName;
	}
	public String getLevel_type() {
		return level_type;
	}
	public void setLevel_type(String level_type) {
		this.level_type = level_type;
	}
	public String getContent_mind() {
		return content_mind;
	}
	public void setContent_mind(String content_mind) {
		this.content_mind = content_mind;
	}
	public String getProject_time() {
		return project_time;
	}
	public void setProject_time(String project_time) {
		this.project_time = project_time;
	}
	public String getContent_description() {
		return content_description;
	}
	public void setContent_description(String content_description) {
		this.content_description = content_description;
	}
	public String getProject_theme() {
		return project_theme;
	}
	public void setProject_theme(String project_theme) {
		this.project_theme = project_theme;
	}

	public int getProj_id() {
		return proj_id;
	}

	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}
	
}
