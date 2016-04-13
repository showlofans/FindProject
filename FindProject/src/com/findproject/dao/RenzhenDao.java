package com.findproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.findproject.DB.DB;
import com.findproject.domain.ProjectBean;
import com.findproject.domain.RenZheng;

public class RenzhenDao {
	private DB db=new DB();
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	private ProjectDAO dao = new ProjectDAO();
	public RenzhenDao() {
		conn=db.getConn();
	}
	public ArrayList<RenZheng> get(String proj_id){
		try{
			ArrayList<RenZheng> list = new ArrayList<RenZheng>();
			String sql = " select * from renzhen where proj_id = '"+proj_id+"'";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			ProjectBean proj = null;
			while (rs.next()) {
				int renzhen_flag = rs.getInt("renzhen_flag");
				String renzhen_info = rs.getString("renzhen_info");
				String renzhen_time = rs.getString("renzhen_time");
				RenZheng renzhen = new RenZheng(proj_id,renzhen_flag, renzhen_info, renzhen_time);
				list.add(renzhen);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.freeCon();
		}
		return null;
	}
	public int getFlag(String proj_id){

		try{
			String sql = " select Max(renzhen_flag) from renzhen where proj_id = '"+proj_id+"' ";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			ProjectBean proj = null;
			while (rs.last()) {
				return rs.getInt(0);
			}
			return 0;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.freeCon();
		}
		return 0;
	
	}
	public String add_renzhen(RenZheng ren){

		String rt="";
		int flag = getFlag(ren.getProj_id())+1;
		String sql_renzhen="insert into	renzhen(proj_id,renzhen_flag,renzhen_info,renzhen_time)values(";
		sql_renzhen =sql_renzhen+"'"+ren.getProj_id()+"',"+"'"+flag+"',"+"'"+ren.getRenzhen_info()+"',"+"'"+ren.getRenzhen_time()+"')";
		try{
			rt=db.execUpdate(sql_renzhen);  //�ɹ�success��ʧ��error
		}catch(Exception e){
			e.printStackTrace();
			rt="error1";
		}		
		return rt;//�ɹ�success��ʧ��error,����exist
	
	}	
}
