package com.findproject.domain;

import java.io.Serializable;
import java.nio.IntBuffer;
import java.sql.ResultSet;


//private String persoName;
//private String level_type;
//private String content_mind;

public class ProjectBean {
	private int project_id; // 项目id
	private String project_link; // 项目链接
	private String project_type; // 项目类型
	private String project_mind;	//项目发布意图
	private String project_theme; // 项目主题(一般是项目名称)
	private String content_description; // 项目介绍
	private String project_time; // 项目发布时间
	private String person_email;
	private PersonBean person;
	private String name;
	
	public ProjectBean() {
		super();
	}
	public void initFromRs(ResultSet rs){
		try{
	    setProject_id(rs.getInt("proj_id"));
		setProject_link(rs.getString("proj_link"));
		setProject_type(rs.getString("proj_type"));
		setProject_mind(rs.getString("proj_mind"));
		setProject_theme(rs.getString("proj_theme"));
		setContent_description(rs.getString("proj_description"));
		setProject_time(rs.getString("proj_time"));
		setPerson_email(rs.getString("proj_mail"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
//	public ProjectBean(int project_id, String project_link,
//			String project_type, String project_mind, String project_theme,
//			String content_description, String project_time,
//			PersonBean person) {
//		super();
//		this.project_id = project_id;
//		this.project_link = project_link;
//		this.project_type = project_type;
//		this.project_mind = project_mind;
//		this.project_theme = project_theme;
//		this.content_description = content_description;
//		this.project_time = project_time;
//		this.person = person;
//	}


	public ProjectBean(String project_link,
			String project_type, String project_mind, String project_theme,
			String content_description, String project_time,String person_email) {
		super();
		this.person_email = person_email;
		this.project_link = project_link;
		this.project_type = project_type;
		this.project_mind = project_mind;
		this.project_theme = project_theme;
		this.content_description = content_description;
		this.project_time = project_time;
	}
//	//3
	public ProjectBean(int project_id, String project_link,
			String project_type, String project_mind, String project_theme,
			String content_description, String project_time ,String person_email) {
		super();
		this.person_email = person_email;
		this.project_id = project_id;
		this.project_link = project_link;
		this.project_type = project_type;
		this.project_mind = project_mind;
		this.project_theme = project_theme;
		this.content_description = content_description;
		this.project_time = project_time;
	}
//
//	//4
//	public ProjectBean(String project_type, String project_mind,
//			String project_theme, String content_description) {
//		super();
//		this.project_type = project_type;
//		this.project_mind = project_mind;
//		this.project_theme = project_theme;
//		this.content_description = content_description;
//	}
//	
//	
	//有项目链接+id deployDetailBean
	public ProjectBean(String project_link,
			String project_type, String project_mind, String project_theme,
			String content_description, String person_email) {
		super();
		this.person_email = person_email;
		this.project_link = project_link;
		this.project_type = project_type;
		this.project_mind = project_mind;
		this.project_theme = project_theme;
		this.content_description = content_description;
	}
//	//没有项目链接+id 5
//	public ProjectBean(String project_id, String project_type,
//			String project_mind, String project_theme,
//			String content_description ,String project_time) {
//		super();
//		this.project_id = project_id;
//		this.project_type = project_type;
//		this.project_mind = project_mind;
//		this.project_theme = project_theme;
//		this.project_time = project_time;
//		this.content_description = content_description;
//	}



	// public ProjectBean(int project_id, String project_type,
	// String project_theme, String content_description,
	// String project_time) {
	// super();
	// this.project_id = project_id;
	// this.project_type = project_type;
	// this.project_theme = project_theme;
	// this.content_description = content_description;
	// this.project_time = project_time;
	// }
	//
	

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

	public String getProject_time() {
		return project_time;
	}

	public void setProject_time(String project_time) {
		this.project_time = project_time;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProject_type() {
		return project_type;
	}

	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}

	public String getProject_link() {
		return project_link;
	}

	public void setProject_link(String project_link) {
		this.project_link = project_link;
	}

	public String getProject_mind() {
		return project_mind;
	}

	public void setProject_mind(String project_mind) {
		this.project_mind = project_mind;
	}
	public String getPerson_email() {
		return person_email;
	}
	public void setPerson_email(String person_email) {
		this.person_email = person_email;
	}
	public PersonBean getPerson() {
		return person;
	}
	public void setPerson(PersonBean person) {
		this.person = person;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
