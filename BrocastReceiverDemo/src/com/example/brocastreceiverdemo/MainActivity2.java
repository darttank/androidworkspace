package com.example.brocastreceiverdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receiver_demo);
	};
		
	
	public void click(View view){
		Intent intent=new Intent();
		intent.setAction("com.example.brocast.nimei");
		sendBroadcast(intent);
		
		
	}
	

}
