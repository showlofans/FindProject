package com.stufinish.findproject.model;

import android.R.string;

public class ScheduleBean {
	private string schedule_id;							  //项目进度ID
	private string project_start;							//项目开始时间
	private string project_finish;							//项目结束时间
	private string schedule_time;							//项目持续时间
	private string schedule_stage;							//项目所处阶段
	
	public ScheduleBean() {
		super();
	}
	public ScheduleBean(string schedule_id, string project_start,
			string project_finish, string schedule_time, string schedule_stage) {
		super();
		this.schedule_id = schedule_id;
		this.project_start = project_start;
		this.project_finish = project_finish;
		this.schedule_time = schedule_time;
		this.schedule_stage = schedule_stage;
	}
	public string getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(string schedule_id) {
		this.schedule_id = schedule_id;
	}
	public string getProject_start() {
		return project_start;
	}
	public void setProject_start(string project_start) {
		this.project_start = project_start;
	}
	public string getProject_finish() {
		return project_finish;
	}
	public void setProject_finish(string project_finish) {
		this.project_finish = project_finish;
	}
	public string getSchedule_time() {
		return schedule_time;
	}
	public void setSchedule_time(string schedule_time) {
		this.schedule_time = schedule_time;
	}
	public string getSchedule_stage() {
		return schedule_stage;
	}
	public void setSchedule_stage(string schedule_stage) {
		this.schedule_stage = schedule_stage;
	}
	
}
