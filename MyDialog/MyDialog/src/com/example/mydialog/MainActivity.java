package com.example.mydialog;

import java.util.List;


import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {

	private Button button1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createDialog().show();
			}
		});
	}
	
	
	private Dialog createDialog()
	{
		final MyDialog dialog = new MyDialog(this, R.style.mydialog);//更改dialog的样式。
		View view = LayoutInflater.from(this).inflate(R.layout.mydialog,
				null);
		dialog.addContentView(view, new LayoutParams(
				android.view.ViewGroup.LayoutParams.FILL_PARENT,
				android.view.ViewGroup.LayoutParams.FILL_PARENT));

		LinearLayout buttonBack = (LinearLayout) view
				.findViewById(R.id.lytBirthdayDialogClose);
		buttonBack.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		Button buttonDetail = (Button) view
				.findViewById(R.id.btnBirthdayDialogDetail);
		buttonDetail.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		return dialog;
	}

}
