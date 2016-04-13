package com.stufinish.findproject.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	//�������ݿ�
	static String name="findproject.db";
	static int dbVersion=2;
	public DatabaseHelper(Context context) {
		super(context, name, null, dbVersion);
	}
	//ֻ�ڴ������ݱ�
	//e_mail project_resource person_name
	public void onCreate(SQLiteDatabase db) {
////		String table_login = "create table register(person_id integer primary key autoincrement"
////				+ "e_mail varchar,person_name varchar(10))";
//		String table_person = "create table tb_register(e_mail varchar primary key,"
//				+ "project_resource varchar,person_name varchar(10))";
//		//ContentBean content_id ,project_trouble ,content_explorer,project_resource,project_mind,project_link ,
//		//项目内容表
//		String table_content = "create table tb_content(content_id varchar primary key,"
//				+ "project_trouble varchar ,content_explorer varchar,"
//				+ "project_resource varchar, project_mind varchar,project_link varchar)";
//		//项目等级表
//		//level_type,contact_gets,comment_gets,project_order,
//		//project_gets,solution_gets
//		String table_level = "create table tb_level(_id Integer primary key,"
//				+ "level_typel varchar,contact_gets Integer,comment_gets Integer,"
//				+ "project_order Integer, project_gets Integer, solution_gets Integer)";
		//项目表
		//id,发布人名字，等级类型 ，发布意图，时间，介绍，主题，认证
		//proj_id,deploy_name,proj_type，proj_mind，deploy_time，
		//proj_description，proj_theme,proj_renzhen
//		String table_project = "create table tb_project(proj_id Integer primary key,"
//				+ "deploy_name varchar,proj_type varchar, proj_mind varchar,"
//				+ "deploy_time varchar, proj_description varchar, proj_theme varchar,"
//				+ "proj_renzhen varchar)";
//		db.execSQL(table_person);//创建个人信息表
//		db.execSQL(table_content);
//		db.execSQL(table_level);
		
		db.execSQL("create table tb_pic(tb_id integer primary key,email varchar(10),pic_file varchar(10))");
		
		
		db.execSQL("create table tb_add (_id integer primary key,deploy_name varchar(10),proj_type varchar(10),"
				+ "proj_mind varchar(10),deploy_time varchar(100),proj_description varchar(200))");// 创建收入信息表
		//db.execSQL("create table tb_add(_id integer primary key,deploy_name varchar(10),proj_type varchar(10),proj_mind varchar(10),deploy_time varchar(10),proj_description varchar(200),proj_theme varchar(10))");
//		db.execSQL(table_project);
	//	db.execSQL(sql1);
		
	}
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
