package com.findproject.add;

import java.sql.Connection;

import com.findproject.DB.DB;
import com.findproject.domain.ProjectBean;

public class AddProject {
	Connection conn = null;
	DB db = new DB();

	public AddProject() {
		conn = db.getConn();
	}

	public String add_project(ProjectBean proj) {
		String rt = "";
		String sql_project = "insert into	project(proj_type,proj_mind,proj_theme,proj_description,proj_time,proj_link,proj_mail)values(";
		sql_project = sql_project + "'" + proj.getProject_type() + "'," + "'"
				+ proj.getProject_mind() + "'," + "'" + proj.getProject_theme()
				+ "'," + "'" + proj.getContent_description() + "'," + "'"
				+ proj.getProject_time() + "'," + "'" + proj.getProject_link()
				+ "'," + "'" + proj.getPerson_email() + "')";
		try {
			rt = db.execUpdate(sql_project); // �ɹ�success��ʧ��error
		} catch (Exception e) {
			e.printStackTrace();
			rt = "error1";
		}
		return rt;// �ɹ�success��ʧ��error,����exist
	}

	// 更新项目链接
	public String modify_res(int proj_id, int proj_link) {
		String rt = "";
		StringBuffer sb = new StringBuffer();
		// update project set proj_link = 'proj_link' where proj_id = 'proj_id'
		sb.append("update project set proj_link = '");
		sb.append("proj_link'");
		sb.append("where proj_id = '");
		sb.append("proj_id'");
		try {
			rt = db.execUpdate(sb.toString()); // �ɹ�success��ʧ��error
		} catch (Exception e) {
			e.printStackTrace();
			rt = "error1";
		}
		return rt;// �ɹ�success��ʧ��error,����exist
	}

	// 更新项目主题
	public String modify_theme(int proj_id, int proj_theme) {
		String rt = "";
		StringBuffer sb = new StringBuffer();
		// update project set proj_link = 'proj_link' where proj_id = 'proj_id'
		sb.append("update project set proj_theme = '");
		sb.append("proj_theme'");
		sb.append("where proj_id = '");
		sb.append("proj_id'");
		try {
			rt = db.execUpdate(sb.toString()); // �ɹ�success��ʧ��error
		} catch (Exception e) {
			e.printStackTrace();
			rt = "error1";
		}
		return rt;// �ɹ�success��ʧ��error,����exist
	}
}
