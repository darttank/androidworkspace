package com.example.listviewasynctask;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView listView;
	private ArrayList<Person> persons;
	private ListViewAdapter adapter;
	private Handler handler;
	
	 final String path="http://192.168.1.107:8080/test/person.xml";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView=(ListView) findViewById(R.id.listview);
		
		Runnable runnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
					
					persons=XmlwebData.getData(path);
					
					handler.sendMessage(handler.obtainMessage(0,persons));
					
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
			}
		};
		
		
		new Thread(runnable).start();
		
		handler=new Handler(){
			public void handleMessage(Message msg){
				if(msg.what==0){
					
					ArrayList<Person> persons=(ArrayList<Person>)msg.obj;
					BinderListData(persons);
					
				}
				
			}
		};
		
		
	}
	// °ó¶¨Êý¾Ý
	public  void BinderListData(ArrayList<Person> person) {
		// TODO Auto-generated method stub
	   adapter=	new ListViewAdapter(R.layout.item, MainActivity.this, persons);
	   listView.setAdapter(adapter);
		
	}
	

	

}
