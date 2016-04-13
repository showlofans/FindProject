package com.stufinish.findproject.model2;


public class PersonLogin {
	private String person_id;
	private String person_name; 				//	--昵称
	private String project_resource; 			//--资源分享平台
	private String e_mail; 						//--邮箱
	
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
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
	
}
