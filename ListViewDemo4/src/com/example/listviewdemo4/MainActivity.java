package com.example.listviewdemo4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView listview;
	List<Map<String, Object>> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview = (ListView) findViewById(R.id.listview);

		// ��ȡ����
		list = getData();

		listview.setAdapter(new ListViewAdapter(list,MainActivity.this));

	}

	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "ͼ1");
		map.put("pictrue", R.drawable.tu1);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "ͼ2");
		map.put("pictrue", R.drawable.tu2);
		list.add(map);
		
		
		map = new HashMap<String, Object>();
		map.put("name", "ͼ3");
		map.put("pictrue", R.drawable.tu3);
		list.add(map);
		
		
		map = new HashMap<String, Object>();
		map.put("name", "ͼ4");
		map.put("pictrue", R.drawable.tu4);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", "ͼ5");
		map.put("pictrue", R.drawable.tu5);
		list.add(map);
		
		
		map = new HashMap<String, Object>();
		map.put("name", "ͼ6");
		map.put("pictrue", R.drawable.tu6);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", "ͼ7");
		map.put("pictrue", R.drawable.tu7);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", "ͼ8");
		map.put("pictrue", R.drawable.tu8);
		list.add(map);
		
		return list;
	}

}
