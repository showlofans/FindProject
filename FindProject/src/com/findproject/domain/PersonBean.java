package com.findproject.domain;

public class PersonBean {
	private String person_name; 				//	--�ǳ�
	private String project_resource; 			//--��Դ����ƽ̨
	private String e_mail; 						//--����
	
	public PersonBean() {
		super();
	}

	
	public PersonBean(String e_mail,
			String project_resource, String person_name) {
		super();
		this.person_name = person_name;
		this.project_resource = project_resource;
		this.e_mail = e_mail;
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
