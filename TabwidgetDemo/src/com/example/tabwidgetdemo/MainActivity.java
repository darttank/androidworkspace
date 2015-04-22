package com.example.tabwidgetdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TabHost tabHost = (TabHost) this.findViewById(android.R.id.tabhost);
		tabHost.setup();
		View view1 = this.getLayoutInflater().inflate(R.layout.custom, null);
	    TextView tv1 = (TextView) view1.findViewById(R.id.tv);
	    tv1.setText("tab1");
	    View view2 = this.getLayoutInflater().inflate(R.layout.custom, null);
	    TextView tv2 = (TextView) view2.findViewById(R.id.tv);
	    tv2.setText("tab2");
	    
	    TabSpec spec1 = tabHost.newTabSpec("tab1").setIndicator(view1).setContent(R.id.tabs1);
	    TabSpec spec2 = tabHost.newTabSpec("tab2").setIndicator(view2).setContent(R.id.tabs2);
	    
	    tabHost.addTab(spec1);
	    tabHost.addTab(spec2);
	    
	}

	

}
