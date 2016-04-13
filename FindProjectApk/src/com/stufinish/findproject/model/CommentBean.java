package com.stufinish.findproject.model;

import android.R.string;

public class CommentBean {
	//comment_id varchar NOT NULL PRIMARY KEY;				//����ID
    private SolutionBean solutionBean;	//���ID
	private string comment_content;						//��������
	private int comment_gets ;							//Ͷ����
	private int project_order;  							//��Ŀ������
	
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
