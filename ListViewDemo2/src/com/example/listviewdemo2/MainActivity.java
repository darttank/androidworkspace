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
		
		//获取数据
		List<String> list=new ArrayList<String>();
		  list.add("测试数据一");
		  list.add("测试数据二");
		  list.add("测试数据三");
		  list.add("测试数据四");
		
		
		listview.setAdapter(new ListViewAdapter(MainActivity.this,list));
	}

	

}
