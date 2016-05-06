//package com.stufinish.findproject.dao;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.stufinish.findproject.model2.Addbean;
//
//public class AddDao {
//	DatabaseHelper helper;
//	SQLiteDatabase db;
//
//	public AddDao(Context context) {
//		super();
//		helper = new DatabaseHelper(context);
//	}
//
//	/**
//	 * 获取项目最大编号
//	 * 
//	 * @return
//	 */
//	public int getMaxId() {
//		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
//		Cursor cursor = db.rawQuery("select max(_id) from tb_add", null);// 获取收入信息表中的最大编号
//		while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
//			return cursor.getInt(0);// 获取访问到的数据，即最大编号
//		}
//		return 0;// 如果没有数据，则返回0
//	}
//
////	/*
////	 * 添加项目信息
////	 * 
////	 * @param projecbean id,发布人名字，等级类型 ，发布意图，时间，介绍，主题，认证
////	 */
////	public void add(Addbean proj) {
////		db = helper.getWritableDatabase();
////		// 执行插入操作
////		db.execSQL(
////				"insert into tb_add (_id,deploy_name,proj_type,proj_mind,deploy_time,proj_description) values (?,?,?,?,?,?)",
////				new Object[] { proj.getProj_id(), proj.getPersoName(),
////						proj.getLevel_type(), proj.getContent_mind(),
////						proj.getProject_time(), proj.getContent_description() });
////	}
//	/**
//	 * 添加支出信息
//	 * 
//	 * @param tb_outaccount
//	 */
//	public void add(Addbean tb_outaccount) {
//		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
//		// 执行添加支出信息操作
//		db.execSQL(
//				"insert into tb_outaccount (_id,money,time,type,address,mark) values (?,?,?,?,?,?)",
//				new Object[] { tb_outaccount.getProj_id(), tb_outaccount.getPersoName(),
//						tb_outaccount.getLevel_type(), tb_outaccount.getContent_mind(),
//						tb_outaccount.getProject_time(), tb_outaccount.getContent_description()});
//	}
//
//	public void init() {
//		Addbean bean1 = new Addbean(1, "xiaoqin", "平台项目", "求关注", "10月19号",
//				"dfjls","nihao");
//		Addbean bean2 = new Addbean(2, "xiaoqin", "平台项目", "求关注", "10月19号",
//				"dfjls", "nihao");
//		Addbean bean3 = new Addbean(3, "xiaoqin", "平台项目", "求关注", "10月19号",
//				"dfjls", "nihao");
//		Addbean bean4 = new Addbean(4, "xiaoqin", "平台项目", "求关注", "10月19号",
//				"dfjls", "nihao");
//		Addbean bean5 = new Addbean(5, "xiaoqin", "平台项目", "求关注", "10月19号",
//				"dfjls", "nihao");
//		add(bean1);
//		add(bean2);
//		add(bean3);
//		add(bean4);
//		add(bean5);
//	}
//
//	/**
//	 * 获取总记录数
//	 * 
//	 * @return
//	 */
//	public long getCount() {
//		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
//		Cursor cursor = db.rawQuery("select count(_id) from tb_add", null);// 获取支出信息的记录数
//		if (cursor.moveToNext())// 判断Cursor中是否有数据
//		{
//			return cursor.getLong(0);// 返回总记录数
//		}
//		return 0;// 如果没有数据，则返回0
//	}
//
//	/**
//	 * 获取项目信息
//	 * 
//	 * @param start
//	 *            起始位置
//	 * @param count
//	 *            每页显示数量
//	 * @return
//	 */
//	public List<Addbean> getScrollData(int start, int count) {
//		List<Addbean> tb_project = new ArrayList<Addbean>();// 创建集合对象
//		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
//		// 获取所有收入信息
//		Cursor cursor = db.rawQuery("select * from tb_add where limit ?,?",
//				new String[] { String.valueOf(start), String.valueOf(count) });
//		while (cursor.moveToNext())// 遍历所有的收入信息
//		{
//
//			// 将遍历到的收入信息添加到集合中
//			// proj_id,deploy_name,proj_type，proj_mind，deploy_time，proj_description，proj_theme,proj_renzhen
//			tb_project.add(new Addbean(cursor.getInt(cursor
//					.getColumnIndex("proj_id")), cursor.getString(cursor
//					.getColumnIndex("deploy_name")), cursor.getString(cursor
//					.getColumnIndex("proj_type")), cursor.getString(cursor
//					.getColumnIndex("proj_mind")), cursor.getString(cursor
//					.getColumnIndex("deploy_time")), cursor.getString(cursor
//					.getColumnIndex("proj_description")), cursor
//					.getString(cursor.getColumnIndex("proj_theme"))));
//		}
//		return tb_project;// 返回集合
//	}
//
//}
