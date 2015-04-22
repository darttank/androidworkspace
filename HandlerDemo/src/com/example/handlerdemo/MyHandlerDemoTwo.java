package com.example.handlerdemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyHandlerDemoTwo extends Activity {
	TextView tv_print_result;
	ArrayList<String> list = new ArrayList<String>();
	String name ;
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			if(msg.what == 100){
				name= (String) msg.obj;
					tv_print_result.append(name);
			
			}
		};
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler_demo2);
		
		tv_print_result = (TextView) findViewById(R.id.tv_handler_result);
		
		MyThread mt = new MyThread();
		new Thread(mt).start();
		
		
	}
	private  class MyThread implements Runnable{
		// TODO Auto-generated method stub
		   int i = 0;
			public void run() {
				try {
					Thread.sleep(1000);
					i++;
					Message msg = Message.obtain();
					msg.obj = "MM"+i;
					msg.what = 100;
					mHandler.sendMessage(msg);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
			
				
				
				
			}
		
		
	}
	
	
	
	

	
}
