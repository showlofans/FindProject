package com.findproject.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TouziDB {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public TouziDB() {
		super();
		conn = DBConnection.getConnection();
	}



	public String deleTouzi(int touzi_id) {
		String tag = "";
		StringBuffer sql = new StringBuffer();
		sql.append("delete touzi where touzi_id = ");
		sql.append(touzi_id+"");
		try {
			stmt = conn.createStatement();
			stmt.execute(sql.toString());
			tag = "success";
		} catch (Exception e) {
			tag = "error";
		} finally {
			DBConnection.close(conn, stmt, rs);
		}
		return tag;
	}

}
