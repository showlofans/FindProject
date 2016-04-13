package com.findproject.domain;

import java.io.Serializable;

public class ContentBean implements Serializable{
	private String project_trouble;		//项目的问题
	private String project_explorer;		//项目实施人员
	private String project_resource;	//项目资源链接
	private String project_mind;		//项目发布意图
	private String project_link;		//项目链接
	
	public ContentBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getProject_trouble() {
		return project_trouble;
	}
	public void setProject_trouble(String project_trouble) {
		this.project_trouble = project_trouble;
	}
	public String getProject_explorer() {
		return project_explorer;
	}
	public void setProject_explorer(String project_explorer) {
		this.project_explorer = project_explorer;
	}
	public String getProject_resource() {
		return project_resource;
	}
	public void setProject_resource(String project_resource) {
		this.project_resource = project_resource;
	}
	public String getProject_mind() {
		return project_mind;
	}
	public void setProject_mind(String project_mind) {
		this.project_mind = project_mind;
	}
	public String getProject_link() {
		return project_link;
	}
	public void setProject_link(String project_link) {
		this.project_link = project_link;
	}
}
