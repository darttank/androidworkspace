package com.tianfan.moneylife;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	public void enter(View view) {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

}
