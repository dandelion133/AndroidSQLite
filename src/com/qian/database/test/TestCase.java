package com.qian.database.test;

import java.util.List;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import com.qian.database.dao.PersonDao;
import com.qian.database.dao.PersonDao2;
import com.qian.database.db.PersonSQLiteOpenHelper;
import com.qian.database.entry.Person;

public class TestCase extends AndroidTestCase {
	
	
	final String Tag = "TestCase";
	public void test() {
		// 数据库什么时候创建
		PersonSQLiteOpenHelper openHelper = new PersonSQLiteOpenHelper(getContext());
		
		// 第一次连接数据库时创建数据库文件. onCreate会被调用
		openHelper.getWritableDatabase();
	}
	
	
	public void testInsert() {
		
		PersonDao dao = new PersonDao(getContext());
		dao.insert(new Person(0,"a",6));
		dao.insert(new Person(0,"q",7));
		dao.insert(new Person(0,"w",8));
		dao.insert(new Person(0,"e",9));
		dao.insert(new Person(0,"a",6));
		dao.insert(new Person(0,"q",7));
		dao.insert(new Person(0,"w",8));
		dao.insert(new Person(0,"e",9));
		dao.insert(new Person(0,"a",6));
		dao.insert(new Person(0,"q",7));
		dao.insert(new Person(0,"w",8));
		dao.insert(new Person(0,"e",9));
		
	}
	public void testDelete() {
			
			PersonDao2 dao = new PersonDao2(getContext());
			dao.delete(4);
			dao.delete(5);
			
	}
		
	
	public void testUpdate() {
		
		PersonDao2 dao = new PersonDao2(getContext());
		dao.update(2, 10000);
		dao.update(1, 1000);
		
	}
	
	public void testQueryAll() {
			
			PersonDao2 dao = new PersonDao2(getContext());
			List<Person> personList = dao.queryAll();
			
			for(Person  person :personList) {
				
				
				Log.i(Tag, person.toString());
				
				
			}
			
			
		}
		
	
	
	
	
	
	public void testQuaryItem() {
		
		PersonDao2 dao = new PersonDao2(getContext());
		Person person = dao.queryItem(2);
		
		Log.i(Tag, person.toString());
		
		
	}
	
	
	
	public void testTansAction() {
		
		// 数据库什么时候创建
		PersonSQLiteOpenHelper openHelper = new PersonSQLiteOpenHelper(getContext());
				
				// 第一次连接数据库时创建数据库文件. onCreate会被调用
		SQLiteDatabase db = openHelper.getWritableDatabase();
		
		if(db.isOpen()) {
			try {
				db.beginTransaction();
				
				
				db.execSQL("update person set balance = balance + 1000 where _id = 1");
				int r = 10/0;
				db.execSQL("update person set balance = balance - 1000 where _id = 2");
				
				db.setTransactionSuccessful();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//Log.i("TansAction", "异常");
				e.printStackTrace();
			}finally {
				db.endTransaction();
			}
			db.close();
		}
		
		
	}
	
	//使用事务可大大减少插入时间
	public void testTransactionInsert() {
		PersonSQLiteOpenHelper openHelper = new PersonSQLiteOpenHelper(getContext());
		SQLiteDatabase db = openHelper.getWritableDatabase();
		
		if(db.isOpen()) {
			
			// 1. 记住当前的时间
			long start = System.currentTimeMillis();
			
			// 2. 开始添加数据
			try {
				db.beginTransaction();
				for (int i = 0; i < 10000; i++) {
					db.execSQL("insert into person(name, age, balance) values('wang" + i + "', " + (10 + i) + ", " + (10000 + i) + ")");
				}
				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}
			
			// 3. 记住结束时间, 计算耗时时间
			long end = System.currentTimeMillis();
			
			long diff = end - start;
			Log.i(Tag, "耗时: " + diff + "毫秒");
			
			db.close();
		}
	}
	
	
	
	
		
}
	
	
	
	
	
	
	
	
	