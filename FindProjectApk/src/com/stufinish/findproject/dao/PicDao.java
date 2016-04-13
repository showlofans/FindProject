package com.stufinish.findproject.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.stufinish.findproject.model2.PersonBean;

public class PicDao {
	private DatabaseHelper dbHelper;

	public PicDao(Context context) {
		dbHelper = new DatabaseHelper(context);
	}
	//第一次为特定邮箱插入图片
	public boolean addPic(PersonBean pb) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "insert into tb_pic(email,pic_file) values(?,?)";
		Object obj[] = { pb.getE_mail(),pb.getPic_file() };
		sdb.execSQL(sql, obj);
		return true;
	}
	//根据邮箱更新图片
	public boolean updatePic(PersonBean pb) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		sdb.execSQL("update tb_pic set pic_file = ? where email = ?", 
				new Object[] {pb.getPerson_name(), pb.getE_mail()});
		return true;
	}
	/*
	 * 根据邮箱获得图片路径
	 */
	public String getPic(String email) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "select * from tb_pic where email=? ";
		Cursor cursor = sdb.rawQuery(sql, new String[] { email });
		while(cursor.moveToNext()){
		String t = cursor.getString(cursor.getColumnIndex("pic_file"));
		return t;
		}
		return "";// 如果没有数据，则返回0
	}
}
