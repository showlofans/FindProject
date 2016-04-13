package com.stufinish.findproject.model;

import android.R.string;

public class Person {
	private string person_id; //	--账号ID
	private int contact_gets;						//	--个人关注数
	private int total_gets;								//--总评级
	private LevelBean levelBean;
	private string person_name; 				//	--昵称
	private string project_resource; 			//--资源分享平台
	private string e_mail; 						//--邮箱
	
	public Person() {
		super();
	}
	public Person(string person_id, int contact_gets, int total_gets,
			string person_name, string project_resource, string e_mail) {
		super();
		this.person_id = person_id;
		this.contact_gets = contact_gets;
		this.total_gets = total_gets;
		this.person_name = person_name;
		this.project_resource = project_resource;
		this.e_mail = e_mail;
	}
	public string getPerson_id() {
		return person_id;
	}
	public void setPerson_id(string person_id) {
		this.person_id = person_id;
	}
	public int getContact_gets() {
		return contact_gets;
	}
	public void setContact_gets(int contact_gets) {
		this.contact_gets = contact_gets;
	}
	public int getTotal_gets() {
		return total_gets;
	}
	public void setTotal_gets(int total_gets) {
		this.total_gets = total_gets;
	}
	public string getPerson_name() {
		return person_name;
	}
	public void setPerson_name(string person_name) {
		this.person_name = person_name;
	}
	public string getProject_resource() {
		return project_resource;
	}
	public void setProject_resource(string project_resource) {
		this.project_resource = project_resource;
	}
	public string getE_mail() {
		return e_mail;
	}
	public void setE_mail(string e_mail) {
		this.e_mail = e_mail;
	}
	public LevelBean getLevelBean() {
		return levelBean;
	}
	public void setLevelBean(LevelBean levelBean) {
		this.levelBean = levelBean;
	}
	
}
