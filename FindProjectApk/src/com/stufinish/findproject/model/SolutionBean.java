package com.stufinish.findproject.model;

import android.R.string;

public class SolutionBean {
	private Person person;			//解答人账号ID
	private string solution_way; //答案类型
	private string solution_content; 					//解答内容
	  
	public SolutionBean(Person person, string solution_way,
			string solution_content) {
		super();
		this.person = person;
		this.solution_way = solution_way;
		this.solution_content = solution_content;
	}
	
	public SolutionBean() {
		super();
	}

	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public string getSolution_way() {
		return solution_way;
	}
	public void setSolution_way(string solution_way) {
		this.solution_way = solution_way;
	}
	public string getSolution_content() {
		return solution_content;
	}
	public void setSolution_content(string solution_content) {
		this.solution_content = solution_content;
	}
	   
}
