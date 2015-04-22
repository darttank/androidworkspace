package com.example.fragmentdemo;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	     addView();
    
}


public void addView(){
    
    FragmentManager fragmentManager = getSupportFragmentManager();
   // HeaderFragment headerFragment = new HeaderFragment();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.frament_details_demo, new FragmentDetails(),"fragment1");
    fragmentTransaction.add(R.id.frament_list_demo,new FragmentList(), "fragment2");
    fragmentTransaction.commit();
    
}





}
