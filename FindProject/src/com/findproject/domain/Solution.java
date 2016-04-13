package com.findproject.domain;;

public class Solution {
	private int _id;			//���id
	private Trouble trouble;  	//�������������
	private String solution_flag;	//�����(���������ݿ��л������ţ�Ȼ��new����)
	private String solution;	//�������
	private Boolean accept_flag;	//�Ƿ��ÿ϶�
	private String solution_time;		//���ʱ��
	public Solution() {
		super();
	}
	//有flag
	public Solution(int _id, Trouble trouble, String solution_flag,
			String solution, Boolean accept_flag, String solution_time) {
		super();
		this._id = _id;
		this.trouble = trouble;
		this.solution_flag = solution_flag;
		this.solution = solution;
		this.accept_flag = accept_flag;
		this.solution_time = solution_time;
	}
	//没有flag
	public Solution(int _id, Trouble trouble, String solution,
			Boolean accept_flag, String solution_time) {
		super();
		this._id = _id;
		this.trouble = trouble;
		this.solution = solution;
		this.accept_flag = accept_flag;
		this.solution_time = solution_time;
	}
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public Trouble getTrouble() {
		return trouble;
	}
	public void setTrouble(Trouble trouble) {
		this.trouble = trouble;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public Boolean getAccept_flag() {
		return accept_flag;
	}
	public void setAccept_flag(Boolean accept_flag) {
		this.accept_flag = accept_flag;
	}
	public String getSolution_time() {
		return solution_time;
	}
	public void setSolution_time(String solution_time) {
		this.solution_time = solution_time;
	}


	public String getSolution_flag() {
		return solution_flag;
	}


	public void setSolution_flag(String solution_flag) {
		this.solution_flag = solution_flag;
	}
	
	
}