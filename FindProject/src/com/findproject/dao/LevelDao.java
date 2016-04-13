package com.findproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import com.findproject.DB.DB;
import com.findproject.domain.LevelBean;
import com.findproject.domain.PersonBean;
import com.findproject.domain.ProjectBean;

public class LevelDao {
	private DB db=new DB();
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	private LevelBean bean;
	
	public LevelDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getGets(String proj_id){
		try{
			ProjectBean pbean = null ;
			String sql = " select contact_gets from project where proj_id = '"+proj_id+"'";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			ProjectBean proj = null;
			while (rs.next()) {
				String contact_gets = rs.getString("contact_gets");
				return contact_gets;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.freeCon();
		}
		return null;
	}
	
	public String updateProject_order(LevelBean level){
		String flag="";
		String sql="update level set project_order='"+level.getProject_order()+1+"'";
		try{
			flag=db.execUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
			flag="error1";
		}
		return flag;
	}
	public String add_level(LevelBean level){
		String rt="";
		//level_type,person(contact_gets),comment(comment_gets,project_order),
		//project_gets,solution_gets
		
		int total_gets = level.getContact_gets() + level.getComment_gets() + level.getProject_order();
		String sql_level="insert into level(email,level_type,contact_gets,comment_gets,project_order,total_gets)values(";
		sql_level =sql_level+"'"+level.getEmail()+"','"+level.getLevel_type()+"',"+"'"+level.getContact_gets()+"',"+"'"+level.getComment_gets()+"',"+"'"+level.getProject_order()+"',"+"'"+total_gets+"')";
		try{
			rt=db.execUpdate(sql_level);  //成功success，失败error
		}catch(Exception e){
			e.printStackTrace();
			rt="error1";
		}		
		return rt;//成功success，失败error,存在exist
	}
	public LevelBean getlevel(String proj_id){
			ResultSet rs=null;
			LevelBean level=null;
			String sql="select * from Level where _id='"+proj_id+"'";
			try{
				rs=db.execSelect(sql);
				while(rs.next())
				{
					TouziDao touziDao = new TouziDao();
					int touzi = touziDao.getTouzi(proj_id);
					level = new LevelBean(rs.getString("email"),rs.getString("level_type"), rs.getInt("total_gets"),
							touzi, rs.getInt("comment_gets"), 
							rs.getInt("project_order"));
					//person.setPerson_id(rs.getString("person_id"));
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				db.freeCon();
			}
			//return (PersonBean) rs;
		return level;
	}
}
