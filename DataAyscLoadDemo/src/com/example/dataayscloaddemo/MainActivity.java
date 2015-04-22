package com.example.dataayscloaddemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.CacheRequest;
import java.util.List;

import com.example.adapter.ContactAdapter;
import com.example.domain.Contact;
import com.example.service.ContactService;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {
	List<Contact> data;
    ListView  listView;
    File  cache;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		cache=new File(Environment.getExternalStorageDirectory(),"cache");
		if(!cache.exists())
			cache.mkdirs();
		
		try {
			
			
			List<Contact>  data = ContactService.GetContacts();
			
			 listView=(ListView)findViewById(R.id.listview);
			  listView.setAdapter(new ContactAdapter(MainActivity.this,data,R.layout.listview_item,cache));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
	}


}
