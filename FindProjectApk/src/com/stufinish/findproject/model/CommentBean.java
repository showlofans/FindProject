package com.stufinish.findproject.model;

import android.R.string;

public class CommentBean {
	//comment_id varchar NOT NULL PRIMARY KEY;				//评论ID
    private SolutionBean solutionBean;	//解答ID
	private string comment_content;						//评论内容
	private int comment_gets ;							//投资数
	private int project_order;  							//项目订阅数
	
	public CommentBean() {
		super();
	}
	public CommentBean(SolutionBean solutionBean, string comment_content,
			int comment_gets, int project_order) {
		super();
		this.solutionBean = solutionBean;
		this.comment_content = comment_content;
		this.comment_gets = comment_gets;
		this.project_order = project_order;
	}
	public SolutionBean getSolutionBean() {
		return solutionBean;
	}
	public void setSolutionBean(SolutionBean solutionBean) {
		this.solutionBean = solutionBean;
	}
	public string getComment_content() {
		return comment_content;
	}
	public void setComment_content(string comment_content) {
		this.comment_content = comment_content;
	}
	public int getComment_gets() {
		return comment_gets;
	}
	public void setComment_gets(int comment_gets) {
		this.comment_gets = comment_gets;
	}
	public int getProject_order() {
		return project_order;
	}
	public void setProject_order(int project_order) {
		this.project_order = project_order;
	}
	   
}
