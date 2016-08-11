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
        
        // ��view�����ListView�Ϳ�����BaseAdapter��������
       mListView.setAdapter(new MyAdapter());
    }

    /**
     * @author andong
     * ����������
     */
    class MyAdapter extends BaseAdapter {

    	private static final String TAG = "MyAdapter";

		/**
    	 * ����ListView�����ݵĳ���
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
		 * �˷������ص���ListView���б���ĳһ�е�View����
		 * position ��ǰ���ص�view������λ�� 
		 * parent ����ListView����
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			
			View view = null;
			//TextView tv = null;
			if(convertView != null) {		// �жϻ�������Ƿ�Ϊnull,  ��Ϊnullʱ�Ѿ������˶���
				Log.i(TAG, "getView: ���û���" + position);
				//tv = (TextView) convertView;
				
				view = convertView;
				
				
			} else {	// ����null, ˵����һ����ʾ, �´���
				Log.i(TAG, "getView: �½�" + position);
				//tv = new TextView(MainActivity.this);
				
				// �������������, ���ڰ�xml����ת����view����
				LayoutInflater inflater = MainActivity.this.getLayoutInflater();
				view = inflater.inflate(R.layout.listview, null);
				
			}
			
			// ��view�е����������丳ֵ
			TextView tvName = (TextView) view.findViewById(R.id.tv_listview_item_name);
			TextView tvAge = (TextView) view.findViewById(R.id.tv_listview_item_age);
						
			Person person = personList.get(position);
			
			tvName.setText("����: " + person.getName());
			tvAge.setText("����: " + person.getAge());
			return view;
			
			/*
			tv.setTextSize(25);
			
			Person person = personList.get(position); // ���ָ��λ�õ�����, ���ж�TextView�İ�
			
			tv.setText(person.toString());
			
			return tv;*/
		}
    	
    }
}
