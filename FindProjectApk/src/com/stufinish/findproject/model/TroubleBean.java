package com.stufinish.findproject.model;

import android.R.string;

public class TroubleBean {
	// trouble_content solution_id solution_admit 
	private string trouble_content;
	private string solution_admit;
	private SolutionBean solutionBean;
	
	public TroubleBean() {
		super();
	}

	public TroubleBean(string trouble_content, string solution_admit,
			SolutionBean solutionBean) {
		super();
		this.trouble_content = trouble_content;
		this.solution_admit = solution_admit;
		this.solutionBean = solutionBean;
	}

	public string getTrouble_content() {
		return trouble_content;
	}

	public void setTrouble_content(string trouble_content) {
		this.trouble_content = trouble_content;
	}

	public string getSolution_admit() {
		return solution_admit;
	}

	public void setSolution_admit(string solution_admit) {
		this.solution_admit = solution_admit;
	}

	public SolutionBean getSolutionBean() {
		return solutionBean;
	}

	public void setSolutionBean(SolutionBean solutionBean) {
		this.solutionBean = solutionBean;
	}
	
	
}
