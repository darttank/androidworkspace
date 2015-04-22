package com.wwj.asyntask;

import java.io.File;
import java.security.MessageDigest;
import java.util.List;

import com.example.asyncload.R;
import com.example.asyncload.R.layout;
import com.example.asyncload.R.menu;
import com.wwj.adapter.ContactAdapter;
import com.wwj.domain.Contact;
import com.wwj.service.ContactService;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivitys extends Activity {
    ListView listView;
    File cache;
    Handler handler = new Handler() {  
    	       public void handleMessage(Message msg) {  
    	           listView.setAdapter(new ContactAdapter(MainActivitys.this,  
    	                   (List<Contact>) msg.obj, R.layout.item, cache));  
    	    }  
       };  

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activitys);
		
	
	       listView = (ListView) this.findViewById(R.id.listview);  
	       
	  cache = new File(Environment.getExternalStorageDirectory(),"cache");
	  
	  if(!cache.exists())
		cache.mkdirs();
	  
	  new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
		  List<Contact> data;
		try {
			data = ContactService.getContacts();

			  handler.sendMessage(handler.obtainMessage(22, data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}).start();
	       
	}
	
	@Override
	protected void onDestroy(){
		for(File file: cache.listFiles()){
			file.delete();
		}
		cache.delete();
		super.onDestroy();
	}
	



}
