package com.findproject.DB;

import java.sql.*;

public class DB {
	private Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;
	PreparedStatement ps = null;

	public DB() {
		// ������TOMcat5.0������������ݿ����
		this.conn = DBConnection.getConnection();
	}

	public Connection getConn() {
		return this.conn;
	}

	public ResultSet execSelect(String sql) {

		try {
			stmt = conn.createStatement(
					java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
					java.sql.ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException se) {

			System.out.print(se.getMessage());
		}
		return rs;
	}

	public String execUpdate(String sql) {
		Statement stmt = null;
		String flag = "";
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate(sql);
			flag = "success";
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			flag = "error";
		}

		return flag;
	}

	public String exec(String sql) {
		Statement stmt = null;
		String flag = "";
		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
			flag = "success";
		} catch (SQLException se) {
			System.out.print(se.getMessage());

			flag = "error";
		}

		return flag;
	}

	public void setConnClose() {
		try {

			conn.close();
		} catch (Exception e) {
		}
	}

	public void freeCon() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException ex) {
		}
	}

	// ����

	public static void main(String[] args) {
		ResultSet rs11 = null;
		ResultSet rsl2 = null;
		DB db1 = new DB();
		PreparedStatement statement = null;
		Statement stmt = null;
		String sql = "select * from person ";
		String sql1 = "select * from person where person_id = ? and sex = ?";
		try {
			stmt = db1.getConn().createStatement();
			statement = db1.getConn().prepareStatement(sql1);
			statement.setInt(1, 123);
			statement.setString(2, "nothing");
			rsl2 = statement.executeQuery();
			rs11 = stmt.executeQuery(sql);
			while (rs11.next()) {

				String i = rs11.getString("username");
				String j = rs11.getString("userpwd");

				System.out.println(i);
				System.out.println(j);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
