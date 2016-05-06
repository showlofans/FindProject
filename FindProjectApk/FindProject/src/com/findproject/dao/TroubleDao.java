package com.findproject.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.findproject.DB.DB;
import com.findproject.DB.DBConnection;
import com.findproject.domain.PersonBean;
import com.findproject.domain.ProjectBean;
import com.findproject.domain.Trouble;

public class TroubleDao {
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	private DB db = new DB();

	public TroubleDao() {
		conn = DBConnection.getConnection();
	}
	public int getCount(int proj_id){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from trouble where proj_id like  '");
		sql.append(proj_id);
		sql.append("%'");
		int count = 0;
		try {
			pstm = conn.prepareStatement(new String(sql));
			rs = pstm.executeQuery();
			while(rs.next()){
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 通过项目id获得问题列表
	 * 
	 * @params proj_id
	 */
	public ArrayList<Trouble> getTrouble(int proj_id) {
		try {
			ArrayList<Trouble> list = new ArrayList<Trouble>();
			StringBuffer sb = new StringBuffer();
			sb.append("select top 5 * from trouble where proj_id like  '");
			sb.append(proj_id);
			sb.append("%'");
			sb.append("order by trouble_flag desc");
			// sb.append("\n");
			// sb.append("select top 5 * from project order by proj_time desc");
			pstm = conn.prepareStatement(sb.toString());
			rs = pstm.executeQuery();
			ProjectBean ProjectBean = null;
			while (rs.next()) {
				Trouble trouble = new Trouble();
				trouble.setTrouble_id(rs.getInt("trouble_id"));
				trouble.setProj_id(rs.getInt("proj_id"));
				trouble.setTrouble_flag(rs.getInt("trouble_flag"));
				trouble.setTrouble_info(rs.getString("trouble_content"));
				trouble.setTrouble_time(rs.getString("trouble_time"));
				trouble.setTrouble_mail(rs.getString("trouble_mail"));
				list.add(trouble);
			}
			if (list.equals("")) {
				return null;
			} else {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// ��return֮ǰִ��
			DBConnection.close(conn, pstm, rs);
		}
		return null;
	}

	public String addTrouble(Trouble trouble) {
		String rt = "";
		String sql = "insert into trouble(trouble_content,proj_id,trouble_time,trouble_flag,trouble_mail)values('"
				+ trouble.getTrouble_info()
				+ "','"
				+ trouble.getProj_id()
				+ "','"
				+ trouble.getTrouble_time()
				+ "','"
				+ trouble.getTrouble_flag()
				+ "','"
				+ trouble.getTrouble_mail() + "')";
		try {
			rt = db.execUpdate(sql); // �ɹ�success��ʧ��error
		} catch (Exception e) {
			e.printStackTrace();
			rt = "error1";
		}
		return rt;
	}

	public ArrayList<Trouble> getTroubles() {

		try {
			ArrayList<Trouble> list = new ArrayList<Trouble>();
			String sql = "select top 5 * from trouble order by trouble_id desc";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Trouble trouble = new Trouble();
				trouble.setTrouble_id(rs.getInt("trouble_id"));
				trouble.setProj_id(rs.getInt("proj_id"));
				trouble.setTrouble_flag(rs.getInt("trouble_flag"));
				trouble.setTrouble_info(rs.getString("trouble_content"));
				trouble.setTrouble_time(rs.getString("trouble_time"));
				list.add(trouble);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstm, rs);
		}

		return null;
	}

}
