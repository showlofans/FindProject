/*package com.stufinish.findproject.dao;


import java.util.ArrayList;
import java.util.List;

import com.stufinish.findproject.model2.ProjectBean;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ProjectDao {

	private DatabaseHelper helper;
	private SQLiteDatabase db;
	
	public ProjectDao(Context context) {
		helper = new DatabaseHelper(context);
	}
	
	 * 添加项目信息
	 * @param projecbean
	 * id,发布人名字，等级类型 ，发布意图，时间，介绍，主题，认证
	 
	public void add(ProjectBean proj) {
		db = helper.getWritableDatabase();
		// 执行插入操作
		db.execSQL(
				"insert into tb_project(proj_id,deploy_name,proj_type,"
						+ "proj_mind,deploy_time,proj_description,proj_theme,"
						+ "proj_renzhen)values(?,?,?,?,?,?,?,?)",
				new Object[] { proj.getProject_id(),
						proj.getPersoName(),
						proj.getLevel_type(),
						proj.getContent_mind(),
						proj.getProject_time(), proj.getContent_description(),
						proj.getProject_theme(), proj.getProject_renzhen() });
	}
	

	*//**
	 * 更新项目信息
	 * //proj_id,deploy_name,proj_type，proj_mind，deploy_time，proj_description，proj_theme,proj_renzhen
	 * @param tb_inaccount
	 */
//	public void update(ProjectBean proj) {
//		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
//		// 执行修改收入信息操作
//		db.execSQL(
//				"update tb_project set deploy_name = ?,proj_type = ?,"
//				+ "proj_mind = ?,deploy_time = ?,proj_description = ?,"
//				+ "proj_theme = ?,proj_renzhen = ? where proj_id = ?",
//				new Object[] {proj.getPersoName(),
//						proj.getLevel_type(),
//						proj.getContent_mind(),
//						proj.getProject_time(), proj.getContent_description(),
//						proj.getProject_theme(), proj.getProject_renzhen(),
//						proj.getProject_id()});
//		
//	}
	/**
	 * 获取项目信息
	 * 
	 * @param start
	 *            起始位置
	 * @param count
	 *            每页显示数量
	 * @return
	 *//*
	public List<ProjectBean> getScrollData(int start, int count, String type) {
		List<ProjectBean> tb_project = new ArrayList<ProjectBean>();// 创建集合对象
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		// 获取所有收入信息
		Cursor cursor = db.rawQuery("select * from tb_project where proj_type = ? limit ?,?",
				new String[] {String.valueOf(type), String.valueOf(start), String.valueOf(count) });
		while (cursor.moveToNext())// 遍历所有的收入信息
		{
			
			ProjectBean projBean = new ProjectBean();
			projBean.setLevel_type(cursor.getString(cursor
					.getColumnIndex("proj_type")));
			projBean.setPersoName(cursor.getString(cursor
					.getColumnIndex("deploy_name")));
			projBean.setContent_mind(cursor.getString(cursor
					.getColumnIndex("proj_mind")));
			
			projBean.setProject_id(cursor.getInt(cursor
					.getColumnIndex("proj_id")));
			projBean.setProject_time(cursor.getString(cursor
					.getColumnIndex("deploy_time")));
			projBean.setContent_description(cursor.getString(cursor
					.getColumnIndex("proj_description")));
			projBean.setProject_theme(cursor.getString(cursor
					.getColumnIndex("proj_theme")));
			projBean.setProject_renzhen(cursor.getString(cursor
					.getColumnIndex("proj_renzhen")));
			// 将遍历到的收入信息添加到集合中
			//proj_id,deploy_name,proj_type，proj_mind，deploy_time，proj_description，proj_theme,proj_renzhen
			tb_project.add(projBean);
		}
		return tb_project;// 返回集合
	}
	
	*//**
	 * 获取项目最大编号
	 * 
	 * @return
	 *//*
	public int getMaxId() {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		Cursor cursor = db.rawQuery("select max(proj_id) from tb_project", null);// 获取收入信息表中的最大编号
		while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
			return cursor.getInt(0);// 获取访问到的数据，即最大编号
		}
		return 0;// 如果没有数据，则返回0
	}
	*//**
	 * 获取总记录数
	 * 
	 * @return
	 *//*
	public long getCount() {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		Cursor cursor = db.rawQuery("select count(proj_id) from tb_project",
				null);// 获取支出信息的记录数
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			return cursor.getLong(0);// 返回总记录数
		}
		return 0;// 如果没有数据，则返回0
	}
}
*/