package com.stufinish.findproject.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.stufinish.findproject.model2.PersonLogin;

public class PwdDAO {
	private DatabaseHelper dbHelper;

	public PwdDAO(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	// µÇÂ¼ÓÃ
	public boolean login(String username, String password) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "select * from user where username=? ";
		Cursor cursor = sdb.rawQuery(sql, new String[] { username});
		if (cursor.moveToFirst() == true) {
			cursor.close();
			return true;
		}
		return false;
	}

	// ×¢²áÓÃ
	public boolean register(PersonLogin pwd) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "insert into user(email,username,resources) values(?,?,?,?)";
		Object obj[] = { pwd.getE_mail(),
				pwd.getPerson_name(), pwd.getProject_resource() };
		sdb.execSQL(sql, obj);
		return true;
	}
}
