package com.stufinish.findproject.model2;

public class Comment {
	private int _id;		//评论id
	private ProjectBean project;		//评论所依附的项目对象
	private NewDescription newdescription;	//评论所依附的介绍对象
	private int comment_flag;   //评论编号
	private String comment;		//评论内容
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(int _id, ProjectBean project, int comment_flag,
			String comment, String comment_time) {
		super();
		this._id = _id;
		this.project = project;
		this.comment_flag = comment_flag;
		this.comment = comment;
		this.comment_time = comment_time;
	}
	public Comment(int _id, ProjectBean project, String comment,
			String comment_time) {
		super();
		this._id = _id;
		this.project = project;
		this.comment = comment;
		this.comment_time = comment_time;
	}
	
	
	public Comment(int _id, NewDescription newdescription, int comment_flag,
			String comment, String comment_time) {
		super();
		this._id = _id;
		this.newdescription = newdescription;
		this.comment_flag = comment_flag;
		this.comment = comment;
		this.comment_time = comment_time;
	}
	public Comment(int _id, NewDescription newdescription, String comment,
			String comment_time) {
		super();
		this._id = _id;
		this.newdescription = newdescription;
		this.comment = comment;
		this.comment_time = comment_time;
	}
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public ProjectBean getProject() {
		return project;
	}
	public void setProject(ProjectBean project) {
		this.project = project;
	}
	public NewDescription getNewdescription() {
		return newdescription;
	}
	public void setNewdescription(NewDescription newdescription) {
		this.newdescription = newdescription;
	}
	public int getComment_flag() {
		return comment_flag;
	}
	public void setComment_flag(int comment_flag) {
		this.comment_flag = comment_flag;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment_time() {
		return comment_time;
	}
	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}
	private String comment_time;	//评论时间
}
