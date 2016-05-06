package com.findproject.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JApplet;

import com.findproject.DB.DBConnection;
import com.findproject.domain.ProjectBean;

public class ProjectDAO {
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;

	// private static ArrayList<LevelBean> levelList;
	// private static ArrayList<PersonBean> personList;
	public ProjectDAO() {
		conn = DBConnection.getConnection();
	}

	// public ProjectBean getProject(int proj_id){
	// try{
	// ProjectBean pbean = null ;
	// String sql = " select * from project where proj_id = '"+proj_id+"'";
	// pstm = conn.prepareStatement(sql);
	// rs = pstm.executeQuery();
	// ProjectBean proj = null;
	// while (rs.next()) {
	// String proj_type = rs.getString("proj_type");
	// String proj_mind = rs.getString("proj_mind");
	// String proj_theme = rs.getString("proj_theme");
	// String proj_description = rs.getString("proj_description");
	// String proj_time = rs.getString("proj_time");
	// pbean = new ProjectBean(proj_id, proj_type, proj_mind, proj_theme,
	// proj_description,proj_time);
	// }
	// return pbean;
	// }catch(Exception e){
	// e.printStackTrace();
	// }finally{
	// db.freeCon();
	// }
	// return null;
	// }

	// project_type project_theme content_description project_trouble
	// project_resource project_link project_mind project_renzhen
	/**
	 * ���⣬���ܣ����⣬��Դ����Ŀ���ӣ�������ͼ�� ��Ŀ��֤������ʱ�䣬�������ǳƣ���Ŀ���ͣ��ȼ�
	 * 
	 * @return
	 */
	// ���ĳһ�׶ε�������Ŀ
	public ArrayList<ProjectBean> find(String email) {
		try {
			ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();
			// levelList = new ArrayList<LevelBean>();
			// personList = new ArrayList<PersonBean>();
			// String sql = "select * from project";
			String sql = "select top 5 * from project where proj_mail!='"
					+ email + "'order by proj_id desc";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			ProjectBean proj = null;
			while (rs.next()) {
				proj = new ProjectBean();
				proj.setProject_id(rs.getInt("proj_id"));
				proj.setProject_link(rs.getString("proj_link"));
				proj.setProject_type(rs.getString("proj_type"));
				proj.setProject_mind(rs.getString("proj_mind"));
				proj.setProject_theme(rs.getString("proj_theme"));
				proj.setContent_description(rs.getString("proj_description"));
				proj.setProject_time(rs.getString("proj_time"));
				proj.setPerson_email(rs.getString("proj_mail"));
				// int proj_id = rs.getInt("proj_id");
				// String proj_link = rs.getString("proj_link");
				// String proj_type = rs.getString("proj_type");
				// String proj_mind = rs.getString("proj_mind");
				// String proj_theme = rs.getString("proj_theme");
				// String proj_description = rs.getString("proj_description");
				// String proj_time = rs.getString("proj_time");
				// String proj_mail = rs.getString("proj_mail");

				// ProjectBean proj = new ProjectBean(proj_id,proj_link,
				// proj_type, proj_mind, proj_theme,
				// proj_description,proj_time,proj_mail);
				// LevelDao levelDao = new LevelDao();
				// LevelBean bean = levelDao.getlevel(proj_id);
				// PersonDao perDao = new PersonDao();
				// PersonBean perBean = perDao.getPerson(proj_mail);
				// String name = perBean.getPerson_name();
				// ProjectBean proj = new ProjectBean(proj_link, proj_type,
				// proj_mind, proj_theme, proj_description,proj_time,proj_mail);
				list.add(proj);
				// levelList.add(bean);
				// personList.add(perBean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstm, rs);
		}
		return null;
	}

	/**
	 * 通过email查找个人信息；
	 * 
	 * @return
	 */
	public ArrayList<ProjectBean> findMProject(String email) {
		try {
			ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();
			StringBuffer sb = new StringBuffer();

			sb.append("select top 5 * from project where proj_mail like  '");
			sb.append(email);
			sb.append("%'");
			sb.append("order by proj_time desc");
			// sb.append("\n");
			// sb.append("select top 5 * from project order by proj_time desc");
			pstm = conn.prepareStatement(sb.toString());
			rs = pstm.executeQuery();
			ProjectBean ProjectBean = null;
			while (rs.next()) {
				ProjectBean proj = new ProjectBean();
				proj.setProject_id(rs.getInt("proj_id"));
				proj.setProject_link(rs.getString("proj_link"));
				proj.setProject_type(rs.getString("proj_type"));
				proj.setProject_mind(rs.getString("proj_mind"));
				proj.setProject_theme(rs.getString("proj_theme"));
				proj.setContent_description(rs.getString("proj_description"));
				proj.setProject_time(rs.getString("proj_time"));
				proj.setPerson_email(rs.getString("proj_mail"));
				list.add(proj);
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

	// 查找不是我的项目个数
	public int getCount(String email, String type) {
		StringBuffer sb = new StringBuffer();
		if (type.equals("项目")) {
			sb.append("select count(*) from project where proj_mail!='");
			sb.append(email);
			sb.append("'");
		} else {
			sb.append("select count(*) from project where proj_mail != '");
			sb.append(email);
			sb.append("'");
			sb.append(" and proj_type = '");
			sb.append(type);
			sb.append("'");
		}
		int count = 0;
		try {
			pstm = conn.prepareStatement(sb.toString());
			rs = pstm.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// myservlet:查找我的项目个数
	public int getMyCount(String email) {
		String sql = "select count(*) from project where proj_mail='" + email
				+ "'";
		int count = 0;
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 分页查询
	 * 
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示的记录
	 * @return 当前页的记录
	 */
	// index sizes email
	public ArrayList<ProjectBean> getAllMyItems(int currentPage, int pageSize,
			String email) {
		ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();
		StringBuffer sql = new StringBuffer();
		int firstResult;
		if (currentPage == 1) {
			firstResult = 0;
		} else if (currentPage == 2 || currentPage == 3) {
			firstResult = (currentPage - 1) * pageSize + currentPage - 2;
		} else {
			firstResult = (currentPage - 1) * pageSize + 4;// (currentPage*pageSize-1)(currentPage-1)*pageSize
		}
		int count = getMyCount(email);
		if (count < pageSize) {
			sql.append("select * from project where proj_mail = '");
			sql.append(email);
			sql.append("' order by proj_id desc");
		} else {
			// sql.append("select * from project where proj_mail = '");
			// sql.append(email);
			// sql.append("' order by proj_id desc");
			sql.append("select top ");
			sql.append(pageSize);
			sql.append("*");
			// sql.append("%'");
			sql.append(" from project where proj_id not in (select top ");
			sql.append(firstResult);
			// sql.append("%'");
			sql.append(" proj_id from project order by proj_id desc) and proj_mail = '");
			sql.append(email);
			sql.append("'");
			sql.append(" order by proj_id desc");
		}
		try {
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			ProjectBean proj = null;
			while (rs.next()) {
				proj = new ProjectBean();
				proj.initFromRs(rs);
				list.add(proj);
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

	/**
	 * 分页查询
	 * 
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示的记录
	 * @return 当前页的记录
	 */
	// index sizes email
	public ArrayList<ProjectBean> getAllItems(int currentPage, int pageSize,
			String email, String type) {
		ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();
		/**
		 * select * from (select row_number()over(order by
		 * proj_id)temprownumber,* from project where proj_mail= 'qq' or admin =
		 * 'qq')tt where temprownumber>0 and temprownumber<11
		 */
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select row_number()over(order by proj_id desc)temprownumber,* from project where proj_mail != '");
		sql.append(email);
		if (!type.equals("项目")) {
			sql.append("' and proj_type = '");
			sql.append(type);
		}
		sql.append("')tt where temprownumber>");
		sql.append((currentPage - 1) * pageSize);
		sql.append("and temprownumber<");
		sql.append(currentPage * pageSize);
		try {
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			ProjectBean proj = null;
			while (rs.next()) {
				proj = new ProjectBean();
				proj.initFromRs(rs);
				list.add(proj);
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

	public ArrayList<ProjectBean> Serch(String str) {
		try {
			ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();
			StringBuffer sql = new StringBuffer();
			sql.append("select top 5* from project where proj_type like  '");
			sql.append(str);
			sql.append("%'");
			sql.append("order by proj_time desc");
			pstm = conn.prepareStatement(sql.toString());// ִ��SQL
			rs = pstm.executeQuery();
			ProjectBean proj = null;
			while (rs.next()) {
				proj = new ProjectBean();
				proj.setProject_id(rs.getInt("proj_id"));
				proj.setProject_link(rs.getString("proj_link"));
				proj.setProject_type(rs.getString("proj_type"));
				proj.setProject_mind(rs.getString("proj_mind"));
				proj.setProject_theme(rs.getString("proj_theme"));
				proj.setContent_description(rs.getString("proj_description"));
				proj.setProject_time(rs.getString("proj_time"));
				proj.setPerson_email(rs.getString("proj_mail"));
				list.add(proj);
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
	// public ArrayList<LevelBean> getLevelList(){
	// return levelList;
	// }
	// public ArrayList<PersonBean> getPersonList(){
	// return personList;
	// }

	// public Boolean exist_project(ProjectBean proj){
	// String sql = "select * from project";
	// try{
	// rs=db.execSelect(sql);
	// //proj_type, proj_mind, proj_theme, proj_description, proj_time
	// if(rs.next()){
	// String proj_type = rs.getString("proj_type");
	// String proj_mind = rs.getString("proj_mind");
	// String proj_theme = rs.getString("proj_theme");
	// String proj_description = rs.getString("proj_description");
	// String proj_time = rs.getString("proj_time");
	// int proj_id = rs.getInt("_id");
	// }
	// }catch(Exception e)
	// {
	// e.printStackTrace();
	// }
	// return false;
	// }

}
