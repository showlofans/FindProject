package com.stufinish.findproject.model;

import android.R.string;

public class ProjectBean {
	//project_id content_id person_id comment_id project_deploy project_trouble 
	private String project_id ;
    private ContentBean content;	//��Ŀ����ID
	private Person person;				//��Ŀ������
	private CommentBean commentBean;		//��Ŀ����ID
	private string project_trouble;					//��Ŀ����
	
	public ProjectBean() {
		super();
	}
	
	public ProjectBean(String project_id, ContentBean content, Person person,
			CommentBean commentBean, string project_trouble) {
		super();
		this.setProject_id(project_id);
		this.content = content;
		this.person = person;
		this.commentBean = commentBean;
		this.project_trouble = project_trouble;
	}

	public ContentBean getContent() {
		return content;
	}
	public void setContent(ContentBean content) {
		this.content = content;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public CommentBean getCommentBean() {
		return commentBean;
	}
	public void setCommentBean(CommentBean commentBean) {
		this.commentBean = commentBean;
	}
	public string getProject_trouble() {
		return project_trouble;
	}
	public void setProject_trouble(string project_trouble) {
		this.project_trouble = project_trouble;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	   
	
}
