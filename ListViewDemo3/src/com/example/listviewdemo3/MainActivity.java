package com.example.listviewdemo3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {
     ListView listview;
     List<Map<String,Object>> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview=(ListView)findViewById(R.id.listview);
		
		//获取数据
		list=getData();
		
		listview.setAdapter(new ListViewAdapter(MainActivity.this,list));
		
		listview.setOnItemClickListener(new OnItemClickListener(
				
				) {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int postion, long id) {
						// TODO Auto-generated method stub
						
						Map<String,Object> map=list.get(postion);
						String name=(String)map.get("name");
						
						
						Toast.makeText(MainActivity.this, "信息:"+name,Toast.LENGTH_SHORT).show();
						
		        		
						
					}
		});
		
	}
	private  List<Map<String,Object>> getData() {
		// TODO Auto-generated method stub
		 List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	     
		 Map<String,Object> map=new HashMap<String, Object>();
		 
		 map.put("name", "图1");
		 map.put("pictrue",R.drawable.tu1);
		 list.add(map);
		 
		 
		 map=new HashMap<String, Object>();
		 
		 map.put("name", "图2");
		 map.put("pictrue",R.drawable.tu2);
		 list.add(map);
		 
		 
          map=new HashMap<String, Object>();
		 
		 map.put("name", "图3");
		 map.put("pictrue",R.drawable.tu3);
		 list.add(map);
		 
		 
         map=new HashMap<String, Object>();
		 
		 map.put("name", "图4");
		 map.put("pictrue",R.drawable.tu4);
		 list.add(map);
		 
		 
		return list;
		
		
		
	}

	

}
