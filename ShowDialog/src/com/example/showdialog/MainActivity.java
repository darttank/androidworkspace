package com.example.showdialog;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void ShowDialog(View v){
		
		DialogDownload dialog = new DialogDownload(MainActivity.this,R.style.MyDialog);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		
	}

}
