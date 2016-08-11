package com.qian.database.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {

	
	
	public PersonSQLiteOpenHelper(Context context) {
		super(context, "myDB2.db", null, 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table person(_id integer primary key, name varchar(20), age integer);";
		db.execSQL(sql);		// ´´½¨person±í
		System.out.println("lashhashfaskhfk");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		if(oldVersion == 1 && newVersion == 2) {
			db.execSQL("alter table person add balance integer;");
			//db.close();
		}
	}

}
