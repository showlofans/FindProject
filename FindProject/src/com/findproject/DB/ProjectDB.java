package com.findproject.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProjectDB {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public ProjectDB() {
		conn = DBConnection.getConnection();
	}

	public String delProject(int proj_id){
		String tag = "";
		StringBuffer sql = new StringBuffer();
		sql.append("delete project where proj_id = ");
		sql.append(proj_id+"");
		try{
		stmt = conn.createStatement();
		stmt.execute(sql.toString());
		tag = "success";
		}catch(Exception e){
			tag = "error";
		}
		finally{
			DBConnection.close(conn, stmt, rs);
		}
		return tag;
	}
}
