package com.tianfan.moneylife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Navorgit extends Activity {

	Button btn_out, btn_in, btn_search, btn_pre, btn_analysis, btn_setting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navorgit_activity);
		btn_out = (Button) findViewById(R.id.btn_in);
		btn_in = (Button) findViewById(R.id.btn_out);
		btn_search = (Button) findViewById(R.id.btn_search);

		btn_pre = (Button) findViewById(R.id.btn_pre);
		btn_analysis = (Button) findViewById(R.id.btn_analysis);

		btn_out.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Navorgit.this, AddEvent.class);
				startActivity(intent);
			}
		});

		btn_in.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Navorgit.this, AddIncome.class);
				startActivity(intent);
			}
		});

		btn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Navorgit.this,
						QueryByDateActivity.class);
				startActivity(intent);
			}
		});

		btn_pre.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Navorgit.this, BudgetActivity.class);
				startActivity(intent);
			}
		});

		btn_analysis.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Navorgit.this,
						AnalysisActivity.class);
				startActivity(intent);
			}
		});

	}

}
