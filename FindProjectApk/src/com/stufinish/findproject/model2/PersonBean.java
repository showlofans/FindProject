package com.stufinish.findproject.model2;

import java.io.Serializable;


public class PersonBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String e_mail; 						//--邮箱（个人id）
	private String person_name; 				//	--昵称
	private String project_resource; 			//--资源分享平台
	private String pic_file;				//用户头像
	
	
	public PersonBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	//有head_img
	public PersonBean(String e_mail, String person_name,
			String project_resource, String pic_file) {
		super();
		this.e_mail = e_mail;
		this.person_name = person_name;
		this.project_resource = project_resource;
		this.pic_file = pic_file;
	}
	

	public PersonBean(String e_mail, String person_name) {
		super();
		this.e_mail = e_mail;
		this.person_name = person_name;
	}

	//没有head_img
	public PersonBean(String e_mail, String person_name, String project_resource) {
		super();
		this.e_mail = e_mail;
		this.person_name = person_name;
		this.project_resource = project_resource;
	}


	public String getPerson_name() {
		return person_name;
	}


	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}


	public String getProject_resource() {
		return project_resource;
	}


	public void setProject_resource(String project_resource) {
		this.project_resource = project_resource;
	}


	public String getE_mail() {
		return e_mail;
	}


	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getPic_file() {
		return pic_file;
	}

	public void setPic_file(String pic_file) {
		this.pic_file = pic_file;
	}

	
	
}
