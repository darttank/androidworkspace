package com.example.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyHandlerDemoOne extends Activity {
	TextView tv_print;
	Button start,end;
    Handler myHanlder = new Handler();
    
    Runnable r = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			tv_print.append("\nŒ“ «ªµ»À");
			myHanlder.postDelayed(r, 1000);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_handler_demo_one);
		end = (Button)findViewById(R.id.end);
		start= (Button) findViewById(R.id.start);
		tv_print = (TextView) findViewById(R.id.tv_print);
		end.setOnClickListener(new MyEndBottonListener());
		start.setOnClickListener(new MyStartBottonListener());
		
	}
	
	

	private class MyEndBottonListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			myHanlder.removeCallbacks(r);
		}

	}

	private class MyStartBottonListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		
			myHanlder.post(r);
		}

	}
}
