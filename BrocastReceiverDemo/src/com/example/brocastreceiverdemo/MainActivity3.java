package com.example.brocastreceiverdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity3 extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
	}
	//发送无序广播--新闻联播
	public void click(View view){
		Intent intent=new Intent();
		intent.setAction("com.example.bro");
		sendBroadcast(intent);
		
	}
	//发送有序广播--中央文件
     public void click2(View view){
    	Intent intent=new Intent();
 		intent.setAction("com.example.bro");
 	    sendOrderedBroadcast(intent, null, null, null, 0, "给农民兄弟的10000元", null);
		
	}


}
