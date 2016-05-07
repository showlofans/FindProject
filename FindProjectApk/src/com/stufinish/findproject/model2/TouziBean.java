package com.stufinish.findproject.model2;

public class TouziBean {
	private int touzi_id;
	private int proj_id;
	private String proj_name;
	private int touzi_gets;
	private String touzi_time;
	private String email;
	
	
	public TouziBean(int touzi_id, int proj_id, String proj_name,
			int touzi_gets, String touzi_time) {
		super();
		this.touzi_id = touzi_id;
		this.proj_id = proj_id;
		this.proj_name = proj_name;
		this.touzi_gets = touzi_gets;
		this.touzi_time = touzi_time;
	}
	public TouziBean(int touzi_id, int proj_id, String proj_name,
			int touzi_gets, String touzi_time, String email) {
		super();
		this.touzi_id = touzi_id;
		this.proj_id = proj_id;
		this.proj_name = proj_name;
		this.touzi_gets = touzi_gets;
		this.touzi_time = touzi_time;
		this.email = email;
	}
	public TouziBean(int proj_id, String proj_name, int touzi_gets,
			String touzi_time, String email) {
		super();
		this.proj_id = proj_id;
		this.proj_name = proj_name;
		this.touzi_gets = touzi_gets;
		this.touzi_time = touzi_time;
		this.email = email;
	}
	public TouziBean(int proj_id, String proj_name, int touzi_gets,
			String touzi_time) {
		super();
		this.proj_id = proj_id;
		this.setProj_name(proj_name);
		this.touzi_gets = touzi_gets;
		this.touzi_time = touzi_time;
	}
	public TouziBean(int proj_id, int touzi_gets, String touzi_time,
			String email) {
		super();
		this.proj_id = proj_id;
		this.touzi_gets = touzi_gets;
		this.touzi_time = touzi_time;
		this.email = email;
	}
	public TouziBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTouzi_id() {
		return touzi_id;
	}
	public void setTouzi_id(int touzi_id) {
		this.touzi_id = touzi_id;
	}
	public int getProj_id() {
		return proj_id;
	}
	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}
	public int getTouzi_gets() {
		return touzi_gets;
	}
	public void setTouzi_gets(int touzi_gets) {
		this.touzi_gets = touzi_gets;
	}
	public String getTouzi_time() {
		return touzi_time;
	}
	public void setTouzi_time(String touzi_time) {
		this.touzi_time = touzi_time;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProj_name() {
		return proj_name;
	}
	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}
	
}
