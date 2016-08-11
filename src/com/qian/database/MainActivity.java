package com.qian.database;



import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qian.database.dao.PersonDao;
import com.qian.database.entry.Person;

public class MainActivity extends Activity {

    private List<Person> personList;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ListView mListView = (ListView) findViewById(R.id.listview);
        
        PersonDao dao = new PersonDao(this);
        personList = dao.queryAll();
        
        // 把view层对象ListView和控制器BaseAdapter关联起来
       mListView.setAdapter(new MyAdapter());
    }

    /**
     * @author andong
     * 数据适配器
     */
    class MyAdapter extends BaseAdapter {

    	private static final String TAG = "MyAdapter";

		/**
    	 * 定义ListView的数据的长度
    	 */
		@Override
		public int getCount() {
			return personList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * 此方法返回的是ListView的列表中某一行的View对象
		 * position 当前返回的view的索引位置 
		 * parent 就是ListView对象
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			
			View view = null;
			//TextView tv = null;
			if(convertView != null) {		// 判断缓存对象是否为null,  不为null时已经缓存了对象
				Log.i(TAG, "getView: 复用缓存" + position);
				//tv = (TextView) convertView;
				
				view = convertView;
				
				
			} else {	// 等于null, 说明第一次显示, 新创建
				Log.i(TAG, "getView: 新建" + position);
				//tv = new TextView(MainActivity.this);
				
				// 布局填充器对象, 用于把xml布局转换成view对象
				LayoutInflater inflater = MainActivity.this.getLayoutInflater();
				view = inflater.inflate(R.layout.listview, null);
				
			}
			
			// 给view中的姓名和年龄赋值
			TextView tvName = (TextView) view.findViewById(R.id.tv_listview_item_name);
			TextView tvAge = (TextView) view.findViewById(R.id.tv_listview_item_age);
						
			Person person = personList.get(position);
			
			tvName.setText("姓名: " + person.getName());
			tvAge.setText("年龄: " + person.getAge());
			return view;
			
			/*
			tv.setTextSize(25);
			
			Person person = personList.get(position); // 获得指定位置的数据, 进行对TextView的绑定
			
			tv.setText(person.toString());
			
			return tv;*/
		}
    	
    }
}
