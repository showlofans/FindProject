package com.findproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.findproject.DB.DB;
import com.findproject.domain.TouziBean;

public class TouziDao {
	private DB db = new DB();
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	private ArrayList<TouziBean> list;
	private TouziBean touziBean;

	// private ProjectDAO dao = new ProjectDAO();
	public TouziDao() {
		conn = db.getConn();
	}
	//获得总投资数
	public int getCountTou(String email){
		int count = 0;
		String sb = "select count(touzi_gets) from project where proj_mail="+email;
		try {
			pstm = conn.prepareStatement(sb);
			rs = pstm.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	public ArrayList<TouziBean> getList(String email) {
		try {
			list = new ArrayList<TouziBean>();
			String sql_touzi = "select * from touzi where email = '" + email
					+ "' order by touzi_id desc";
			pstm = conn.prepareStatement(sql_touzi);
			rs = pstm.executeQuery();
			while (rs.next()) {
				touziBean = new TouziBean(rs.getInt("proj_id"),rs.getString("proj_theme"), rs.getInt("touzi_gets"), 
						rs.getString("touzi_time"), rs.getString("email"));
				list.add(touziBean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.freeCon();
		}
		return null;
	}

	// �����Ŀid���Ͷ����
	public int getTouzi(int proj_id) {
		String sql_gets = "select touzi_gets from touzi where proj_id = '"
				+ proj_id + "'";
		try {
			rs = db.execSelect(sql_gets);
			while (rs.next()) {
				int touzi_gets = rs.getInt("touzi_gets");
				return touzi_gets;
				// person.setPerson_id(rs.getString("person_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.freeCon();
		}
		return 0;
	}

	// Ͷ�ʰ�ť������Ͷ����
	public String updateTouzi(TouziBean touzi) {
		String flag = "";
		int touzi_gets = touzi.getTouzi_gets() + getTouzi(touzi.getProj_id());
		String sql = "update touzi set touzi_gets='" + touzi_gets + "'";
		try {
			flag = db.execUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			flag = "error1";
		}
		return flag;
	}

	// String proj_id, int touzi_gets, String touzi_time,
	// String email
	public String addTouzi(TouziBean touzi) {
		String rt = "";
		String sql_touzi = "insert into touzi(proj_id,touzi_gets,touzi_time,email,proj_theme)values(";
		sql_touzi = sql_touzi + "'" + touzi.getProj_id() + "'," + "'"
				+ touzi.getTouzi_gets() + "'," + "'" + touzi.getTouzi_time()
				+ "'," + "'" + touzi.getEmail() + "'," + "'" + touzi.getProj_name() + "')";
		try {
			rt = db.execUpdate(sql_touzi); // �ɹ�success��ʧ��error
		} catch (Exception e) {
			e.printStackTrace();
			rt = "error1";
		}
		return rt;// �ɹ�success��ʧ��error,����exist
	}
}
