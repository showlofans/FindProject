package com.stufinish.findproject.dao;

import com.stufinish.findproject.model2.PersonBean;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RegisterDao {
	private DatabaseHelper helper;
	private SQLiteDatabase db;
	public RegisterDao(Context context) {
		helper = new DatabaseHelper(context);
	}

	/**
	 * ���ע����Ϣ
	 * 
	 * @param tb_pwd
	 */
	public void register(PersonBean tb_reg) {
		db = helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����
		// ִ������������
		db.execSQL("insert into tb_register (e_mail,project_resource,person_name) values (?,?,?)",
				new Object[] { tb_reg.getE_mail(),tb_reg.getProject_resource(),tb_reg.getPerson_name() });
	}
	
	/**
	 * ���ע����Ϣ
	 * 
	 * @param tb_pwd
	 */
	public void dele_register(PersonBean tb_reg) {
		db = helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����
		// ִ������������
		db.execSQL("insert into tb_register (e_mail,project_resource,person_name) values (?,?,?)",
				new Object[] { tb_reg.getE_mail(),tb_reg.getProject_resource(),tb_reg.getPerson_name() });
	}
	/**
	 * 查找个人信息
	 * 
	 * @return
	 */
	public Boolean check(String e_mail) {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		// 查找密码并存储到Cursor类中
		Cursor cursor = db.rawQuery("select e_mail from tb_register where e_mail = ?", 
				new String[] { String.valueOf(e_mail) });
		if (cursor.moveToNext())// 遍历查找到的密码信息
		{
			return false;
//			// 将密码存储到Tb_pwd类中
//			return new PersonBean(cursor.getString(cursor.getColumnIndex("person_name")),
//					cursor.getString(cursor.getColumnIndex("project_resource")),
//					cursor.getString(cursor.getColumnIndex("e_mail")));
		}
		return true;// 如果没有找到信息
	}
	
	public PersonBean find(String e_mail) {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		// 查找密码并存储到Cursor类中
		Cursor cursor = db.rawQuery("select e_mail,project_resource,person_name from tb_register where e_mail = ?", 
				new String[] { String.valueOf(e_mail) });
		if (cursor.moveToNext())// 遍历查找到的密码信息
		{
			// 将密码存储到Tb_pwd类中
			return new PersonBean(cursor.getString(cursor.getColumnIndex("person_name")),
					cursor.getString(cursor.getColumnIndex("project_resource")),
					cursor.getString(cursor.getColumnIndex("e_mail")));
		}
		return null;// 如果没有信息，则返回null
	}
	
	public String getName(String e_mail) {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		// 查找密码并存储到Cursor类中
		Cursor cursor = db.rawQuery("select person_name from tb_register where e_mail = ?", new String[]{String.valueOf(e_mail)});
		if(cursor.moveToNext()){
		return cursor.getString(cursor.getColumnIndex("person_name"));}
		return null;
	}
	/**
	 * 设置个人信息
	 * e_mail project_resource person_name
	 * @param tb_reg
	 */
	public void update(PersonBean tb_reg) {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		// 执行修改密码操作
		db.execSQL("update tb_register set e_mail = ?,project_resource = ?,person_name = ?",
				new Object[] { tb_reg.getE_mail(),tb_reg.getProject_resource(),tb_reg.getPerson_name() });
	}
	
//	public long getUser() {
//		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
//		Cursor cursor = db.rawQuery("select count(e_mail) from tb_pwd", null);// 获取密码信息的记录数
//		if (cursor.moveToNext())// 判断Cursor中是否有数据
//		{
//			return cursor.getLong(0);// 返回总记录数
//		}
//		return 0;// 如果没有数据，则返回0
//	}

}
