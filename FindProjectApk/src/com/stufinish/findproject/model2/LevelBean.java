package com.stufinish.findproject.model2;


public class LevelBean {
	//level_type,person(contact_gets),comment(comment_gets,project_order),
	//project_gets,solution_gets
	private String _id;			//等级id
	private PersonBean person;		//等级所依附的个人
	private String email;
	private String level_type;
	private int total_gets;								//--总评级
	private int contact_gets;
	private int comment_gets;
	private int project_order;

	public LevelBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public LevelBean(String email, String level_type, int total_gets,
			int contact_gets, int comment_gets, int project_order) {
		super();
		this.email = email;
		this.level_type = level_type;
		this.total_gets = total_gets;
		this.contact_gets = contact_gets;
		this.comment_gets = comment_gets;
		this.project_order = project_order;
	}

	
	public LevelBean(String email, String level_type, int contact_gets,
			int comment_gets, int project_order) {
		super();
		this.email = email;//或者——id
		this.level_type = level_type;
		this.contact_gets = contact_gets;
		this.comment_gets = comment_gets;
		this.project_order = project_order;
	}


	public LevelBean(String level_type, int total_gets, int contact_gets,
			int comment_gets, int project_order) {
		super();
		this.level_type = level_type;
		this.total_gets = total_gets;
		this.contact_gets = contact_gets;
		this.comment_gets = comment_gets;
		this.project_order = project_order;
	}


	public LevelBean(PersonBean person, String level_type, int contact_gets,
			int comment_gets, int project_order ,int total_gets) {
		super();
		this.total_gets = total_gets;
		this.person = person;
		this.level_type = level_type;
		this.contact_gets = contact_gets;
		this.comment_gets = comment_gets;
		this.project_order = project_order;
	}


	//有personbean的
	public LevelBean(String _id, PersonBean person, String level_type,
			int total_gets, int contact_gets, int comment_gets,
			int project_order) {
		super();
		this._id = _id;
		this.person = person;
		this.level_type = level_type;
		this.total_gets = total_gets;
		this.contact_gets = contact_gets;
		this.comment_gets = comment_gets;
		this.project_order = project_order;
	}

	public String getLevel_type() {
		return level_type;
	}
	public void setLevel_type(String level_type) {
		this.level_type = level_type;
	}
	public int getContact_gets() {
		return contact_gets;
	}
	public void setContact_gets(int contact_gets) {
		this.contact_gets = contact_gets;
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

	public int getTotal_gets() {
		return total_gets;
	}

	public void setTotal_gets(int total_gets) {
		this.total_gets = total_gets;
	}

	public PersonBean getPerson() {
		return person;
	}

	public void setPerson(PersonBean person) {
		this.person = person;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
