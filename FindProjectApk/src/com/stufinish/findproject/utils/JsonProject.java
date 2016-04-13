package com.stufinish.findproject.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.stufinish.findproject.model2.ProjectBean;

public class JsonProject {

	public JsonProject() {
		super();
	}
	public static List<ProjectBean> jxJSON(String result) {
		List<ProjectBean> ll = new ArrayList<ProjectBean>();
		if (result == null) {
			return null;
		}
		try {
			JSONArray jsonarr = new JSONArray(result);
			for (int i = 0; i < jsonarr.length(); i++) {
				JSONObject obj = jsonarr.getJSONObject(i);
//				int proj_id = obj.getInt("project_id");
				String proj_link = obj.getString("project_link");
				String proj_type = obj.getString("project_type");
				String proj_mind = obj.getString("project_mind");
				String proj_theme = obj.getString("project_theme");
				String proj_description = obj.getString("content_description");
				String proj_time = obj.getString("project_time");
				String proj_mail = obj.getString("person_email");
//				String person_name = obj.getString("name");
//				String person_resource = obj.getString("project_resource");
//				personBean = new PersonBean(proj_mail, person_name);
//				pbean = new ProjectBean(proj_id, proj_link, proj_type, proj_mind, proj_theme, proj_description, proj_time, proj_mail, person_name);
				ProjectBean pbean = new ProjectBean(proj_link, proj_type,proj_mind, proj_theme, proj_description, proj_time,proj_mail);
				ll.add(pbean);
			}
			return ll;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
