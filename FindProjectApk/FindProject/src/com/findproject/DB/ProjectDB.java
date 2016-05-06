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

	// 根据项目id，删除项目
	/**
	 * @params proj_id
	 * @return boolean 删除结果
	 */
	public String delProject(int proj_id) {
		Boolean result = false;
		String flag = "";
		try {
			String sql = "delete from project where proj_id ="+ proj_id ;
			stmt = conn.createStatement();
			stmt.execute(sql);
			flag = "success";
		} catch (Exception e) {
			flag = "error";
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, stmt, rs);
		}
		return flag;
	}

}
