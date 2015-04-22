package com.example.jdscdemo;
import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
   TabHost tabHost;
   private RadioButton main_tab_home, main_tab_catagory, main_tab_car,
	main_tab_buy, main_tab_more;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initTab();
        init();
	}
	
	 public void init(){
	    	main_tab_home=(RadioButton)findViewById(R.id.main_tab_home);
	    	main_tab_catagory = (RadioButton) findViewById(R.id.main_tab_catagory);
			main_tab_car = (RadioButton) findViewById(R.id.main_tab_car);
			main_tab_buy = (RadioButton) findViewById(R.id.main_tab_buy);
			main_tab_more = (RadioButton) findViewById(R.id.main_tab_more);
			main_tab_home.setOnClickListener(new OnClickListener() {

				public void onClick(View view) {
					tabHost.setCurrentTabByTag("home");

				}
			});

			main_tab_catagory.setOnClickListener(new OnClickListener() {

				public void onClick(View view) {
					tabHost.setCurrentTabByTag("catagory");

				}
			});
			main_tab_car.setOnClickListener(new OnClickListener() {

				public void onClick(View view) {
					tabHost.setCurrentTabByTag("car");

				}
			});
			main_tab_buy.setOnClickListener(new OnClickListener() {

				public void onClick(View view) {
					tabHost.setCurrentTabByTag("buy");

				}
			});
			main_tab_more.setOnClickListener(new OnClickListener() {

				public void onClick(View view) {
					tabHost.setCurrentTabByTag("viewpager2");

				}
			});
	    }
	    
	    public void initTab(){
	    	tabHost=getTabHost();
	    	tabHost.addTab(tabHost.newTabSpec("home").setIndicator("home")
					.setContent(new Intent(this, HomeActivity.class)));
	    	tabHost.addTab(tabHost.newTabSpec("catagory").setIndicator("catagory")
					.setContent(new Intent(this, CategoryActivity.class)));
			tabHost.addTab(tabHost.newTabSpec("car").setIndicator("car")
					.setContent(new Intent(this, CarActivity.class)));
			tabHost.addTab(tabHost.newTabSpec("buy").setIndicator("buy")
					.setContent(new Intent(this, BuyActivity.class)));
			tabHost.addTab(tabHost.newTabSpec("viewpager2").setIndicator("viewpager2")
					.setContent(new Intent(this, ViewPagerActivityTwo.class)));
	    }
	    


}
