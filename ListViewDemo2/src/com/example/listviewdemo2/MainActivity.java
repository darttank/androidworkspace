package com.example.listviewdemo2;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview=(ListView) findViewById(R.id.listview);
		
		//��ȡ����
		List<String> list=new ArrayList<String>();
		  list.add("��������һ");
		  list.add("�������ݶ�");
		  list.add("����������");
		  list.add("����������");
		
		
		listview.setAdapter(new ListViewAdapter(MainActivity.this,list));
	}

	

}
