package com.tianfan.moneylife;

import java.util.ArrayList;
import java.util.List;

import com.tianfan.bean.MyLogin;
import com.tianfan.db.DBHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tianfan.db.*;

public class LoginActivity extends Activity {

	Button btn_register, bnt_login;
	Intent intent;
	EditText edit_login_username, edit_login_pwd;
	private com.tianfan.db.DBHelper dbhelper;
	private SQLiteDatabase db;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn_register = (Button) findViewById(R.id.btn_register);
		bnt_login = (Button) findViewById(R.id.btn_login);

		edit_login_username = (EditText) findViewById(R.id.edit_login_username);
		edit_login_pwd = (EditText) findViewById(R.id.edit_login_pwd);

		// µÇÂ½
		bnt_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				boolean flag = CheckLogin();
				if (flag == true) {
					Toast.makeText(LoginActivity.this, "¹§Ï²µÇÂ½³É¹¦", 1000).show();
					intent = new Intent(LoginActivity.this, MainActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(LoginActivity.this, "ÓÃ»§Ãû»òÃÜÂë´íÎó", 1000).show();
				}
			}
		});

		// ×¢²á
		btn_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);

			}
		});

	}

	public boolean CheckLogin() {

		// ÅÐ¶ÏµÇÂ½ÐÅÏ¢ÊÇ·ñÆ¥Åä
		List<MyLogin> MyLoginList = new ArrayList<MyLogin>();
		dbhelper = new DBHelper(LoginActivity.this);
		db = dbhelper.getWritableDatabase();

		Cursor cu = db.rawQuery("select * from tb_user", null);
		while (cu.moveToNext()) {

			MyLoginList.add(new MyLogin(cu.getString(cu
					.getColumnIndex("username")), cu.getString(cu
					.getColumnIndex("password"))));

		}
		for (int i = 0; i < MyLoginList.size(); i++) {
			Log.i("tianfanList", MyLoginList.get(i).getPassword());
			Log.i("tianfanList", MyLoginList.get(i).getUsername());
		}
		for (int i = 0; i < MyLoginList.size(); i++) {
			if (MyLoginList.get(i).getPassword()
					.equals(edit_login_username.getText().toString())
					&& MyLoginList.get(i).getUsername()
							.equals(edit_login_pwd.getText().toString())) {
				return true;
			}

		}
		return false;

	}

}

/*
 * public void enter(View view) {
 * 
 * 
 * 
 * Intent intent = new Intent(this, MainActivity.class); startActivity(intent);
 * }
 */

