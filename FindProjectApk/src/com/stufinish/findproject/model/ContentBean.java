package com.stufinish.findproject.model;

import android.R.string;

public class ContentBean {
	// schedule_id ,project_theme ,content_description ,
	//trouble_id ,content_explorer,content_resource 
	private ScheduleBean scheduleBean;
	private string project_theme;
	private string content_decription;
	private string content_explorer;
	private string content_resource;
	private  TroubleBean troubleBean;
	
	public ContentBean() {
		super();
	}

	public ContentBean(ScheduleBean scheduleBean, string project_theme,
			string content_decription, string content_explorer,
			string content_resource, TroubleBean troubleBean) {
		super();
		this.scheduleBean = scheduleBean;
		this.project_theme = project_theme;
		this.content_decription = content_decription;
		this.content_explorer = content_explorer;
		this.content_resource = content_resource;
		this.troubleBean = troubleBean;
	}

	public ScheduleBean getScheduleBean() {
		return scheduleBean;
	}

	public void setScheduleBean(ScheduleBean scheduleBean) {
		this.scheduleBean = scheduleBean;
	}

	public string getProject_theme() {
		return project_theme;
	}

	public void setProject_theme(string project_theme) {
		this.project_theme = project_theme;
	}

	public string getContent_decription() {
		return content_decription;
	}

	public void setContent_decription(string content_decription) {
		this.content_decription = content_decription;
	}

	public string getContent_explorer() {
		return content_explorer;
	}

	public void setContent_explorer(string content_explorer) {
		this.content_explorer = content_explorer;
	}

	public string getContent_resource() {
		return content_resource;
	}

	public void setContent_resource(string content_resource) {
		this.content_resource = content_resource;
	}

	public TroubleBean getTroubleBean() {
		return troubleBean;
	}

	public void setTroubleBean(TroubleBean troubleBean) {
		this.troubleBean = troubleBean;
	}
	
	
}
