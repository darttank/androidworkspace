package com.example.viewpagerdemo;



import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.*;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    ViewPager viewPager;
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		viewPager=(ViewPager) findViewById(R.id.viewpager);
		
		LayoutInflater layoutInflater=getLayoutInflater().from(this);
		View view1=layoutInflater.inflate(R.layout.view1, null);
		View view2=layoutInflater.inflate(R.layout.view2, null);
		View view3=layoutInflater.inflate(R.layout.view3, null);
		View view4=layoutInflater.inflate(R.layout.view4, null);
		
		List<View> list=new ArrayList<View>();
		
		list.add(view1);
		list.add(view2);
		list.add(view3);
		list.add(view4);
		viewPager.setAdapter(new MyViewPagerAdapter(list));
		viewPager.setCurrentItem(0);
		
	}
	
	public void enter(View view){
		Intent intent = new Intent(getApplicationContext(), OtherActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onStop() {
		finish();
		super.onStop();
	}



}
