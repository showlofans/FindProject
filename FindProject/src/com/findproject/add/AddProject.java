package com.findproject.add;

import java.sql.Connection;

import com.findproject.DB.DB;
import com.findproject.domain.ProjectBean;


public class AddProject {
	Connection conn=null;
	DB db=new DB();
	
	public AddProject() {
		conn = db.getConn();
	}
	public String add_project(ProjectBean proj){
		String rt="";
		String sql_project="insert into	project(proj_type,proj_mind,proj_theme,proj_description,proj_time,proj_link,proj_mail)values(";
		sql_project =sql_project+"'"+proj.getProject_type()+"',"+"'"+proj.getProject_mind()+"',"+"'"+proj.getProject_theme()+"',"+"'"+proj.getContent_description()+"',"+"'"+proj.getProject_time()+"',"+"'"+proj.getProject_link()+"',"+"'"+proj.getPerson_email()+"')";
		try{
			rt=db.execUpdate(sql_project);  //�ɹ�success��ʧ��error
		}catch(Exception e){
			e.printStackTrace();
			rt="error1";
		}		
		return rt;//�ɹ�success��ʧ��error,����exist
	}
	public String modify_project(){
		
		return null;
	}
}
