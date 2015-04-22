package com.example.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//StartIntent();
		
	}

	public void enter(View view) {
		// TODO Auto-generated method stub
		//œ‘ æ“‚Õº
		Intent intent =new Intent();
		intent.setClassName(getPackageName(), "com.example.intentdemo.OtherActivity");
		startActivity(intent);
	}
	

	

}
