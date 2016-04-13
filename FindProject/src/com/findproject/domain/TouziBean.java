package com.findproject.domain;

public class TouziBean {
	private String touzi_id;
	private String proj_id;
	private String proj_name;
	private int touzi_gets;
	private String touzi_time;
	private String email;
	
	public TouziBean(String proj_id, String proj_name, int touzi_gets,
			String touzi_time) {
		super();
		this.proj_id = proj_id;
		this.proj_name = proj_name;
		this.touzi_gets = touzi_gets;
		this.touzi_time = touzi_time;
	}
	public TouziBean(String proj_id, int touzi_gets, String touzi_time,
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
	public String getTouzi_id() {
		return touzi_id;
	}
	public void setTouzi_id(String touzi_id) {
		this.touzi_id = touzi_id;
	}
	public String getProj_id() {
		return proj_id;
	}
	public void setProj_id(String proj_id) {
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
	
}
