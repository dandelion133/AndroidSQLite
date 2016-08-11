package com.qian.database.dao;
//使用API方式
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.qian.database.db.PersonSQLiteOpenHelper;
import com.qian.database.entry.Person;

public class PersonDao2 {
	// 数据库的帮助类对象
	final String Tag = "Person2";
	private PersonSQLiteOpenHelper mOpenHelper;

	public PersonDao2(Context context) {
		
		mOpenHelper = new PersonSQLiteOpenHelper(context);

	}
	public void insert(Person person) {
		
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();    
		
		if(db.isOpen()) {
			
			
			
			ContentValues values = new ContentValues();
			values.put("name", person.getName());
			values.put("age", person.getAge());
			db.insert("person", null, values);//nullColumnHack 当values为空时有用
			
			
			db.close();
			
			
		}
		
		
	}
	
	
	
	public void delete(int id) {
		
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();   
		if(db.isOpen()) {
			
			//db.execSQL("delete from person where _id = ?;", new Integer[]{id});
			
			
			
			String whereClause = "_id = ?";
			String[] whereArgs = new String[]{id + ""};
			int cnt = db.delete("person", whereClause, whereArgs);
			
			Log.i(Tag, "删除了"+ cnt +"行");
			db.close();
		}
		
	}
	
	
	
	public void update(int id,int money) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();   
		if(db.isOpen()) {
			//db.execSQL("update person set name = ? where _id = ?;", new Object[] {name,id});
			
			
			ContentValues values = new ContentValues();
			values.put("balance", money);
			String whereClause = "_id = ?";
			String[] whereArgs = new String[]{id + ""};
			db.update("person", values, whereClause, whereArgs);
			
			db.close();
		}
		
	}
	
	
	
	public List<Person> queryAll() {
		
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();  
		List<Person> personList = new ArrayList<Person>();
		if(db.isOpen()) {
			
			String[] columns = new String[]{"_id","name","age"};// 需要的列
			String selection = null;// 选择条件, 给null查询所有
			String[] selectionArgs = null;// 选择条件的参数, 会把选择条件中的? 替换成数据中的值
			String groupBy = null;// 分组语句  group by name
			String having = null;// 过滤语句
			String orderBy = null;// 排序
			//Cursor cursor = db.rawQuery("select _id,name,age from person",null );
			Cursor cursor = db.query("person", columns, selection, selectionArgs, groupBy, having, orderBy);
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
			String[] columns = new String[]{"_id","name","age"};// 需要的列
			String selection = "_id = ?";// 选择条件, 给null查询所有
			String[] selectionArgs = new String[]{_id + ""};// 选择条件的参数, 会把选择条件中的? 替换成数据中的值
			String groupBy = null;// 分组语句  group by name
			String having = null;// 过滤语句
			String orderBy = null;// 排序
			Cursor cursor = db.query("person", columns, selection, selectionArgs, groupBy, having, orderBy);
					
			//"select _id,name,age from person where _id = ?",new String[]{_id+""} );		
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
