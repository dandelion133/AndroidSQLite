package com.qian.database.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qian.database.db.PersonSQLiteOpenHelper;
import com.qian.database.entry.Person;

public class PersonDao {
	// 数据库的帮助类对象
	private PersonSQLiteOpenHelper mOpenHelper;

	public PersonDao(Context context) {
		
		mOpenHelper = new PersonSQLiteOpenHelper(context);

	}
	public void insert(Person person) {
		
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();    
		
		if(db.isOpen()) {
			
			db.execSQL("insert into person(name,age) values(?,?);",
					new Object[] { person.getName(), person.getAge() });
			
			//db.execSQL("insert into person(name, age) value(?,  ?);",new Object[] { person.getName(), person.getAge() });
			///db.execSQL("insert into person(name, age) values(?, ?);", new Object[]{person.getName(), person.getAge()});
			db.close();
			
			
		}
		
		
	}
	
	
	
	public void delete(int id) {
		
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();   
		if(db.isOpen()) {
			
			db.execSQL("delete from person where _id = ?;", new Integer[]{id});
			db.close();
		}
		
	}
	
	
	
	public void update(int id,String name) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();   
		if(db.isOpen()) {
			db.execSQL("update person set name = ? where _id = ?;", new Object[] {name,id});
			db.close();
		}
		
	}
	
	
	
	public List<Person> queryAll() {
		
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();  
		List<Person> personList = new ArrayList<Person>();
		if(db.isOpen()) {
			
			Cursor cursor = db.rawQuery("select _id,name,age from person",null );
			if(cursor != null && cursor.getCount() > 0) {
				int id;
				String name;
				int age;
				while(cursor.moveToNext()) {
					
					id = cursor.getInt(0);
					name = cursor.getString(1);
					age = cursor.getInt(2);
					personList.add(new Person(id,name,age));
					
				}
			}
			
			db.close();
			return personList;
		}
		db.close();
		return null;
	}
	
	
	
public Person queryItem(int _id) {
		
	
	SQLiteDatabase db = mOpenHelper.getWritableDatabase();  
		
		if(db.isOpen()) {
			int id;
			String name;
			int age;	
			Cursor cursor = db.rawQuery("select _id,name,age from person where _id = ?",new String[]{_id+""} );
			if(cursor != null && cursor.moveToFirst()) {
				
			
					
				id = cursor.getInt(0);
				name = cursor.getString(1);
				age = cursor.getInt(2);
				//personList.add(new Person(id,name,age));
					
				db.close();
				return new Person(id,name,age);
			}
			db.close();
			
		}
		//db.close();
		return null;
	}
	
	
	
	
}
