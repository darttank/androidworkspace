package com.lottery.rotary.mobile.android.activity;

import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lottery.rotary.mobile.android.R;
import com.lottery.rotary.mobile.android.json.LoginJson;
import com.lottery.rotary.mobile.android.tools.PreferencesTools;

/**
 * 登陆界面
 * 
 * @author Alin
 * @time 2014-11-7上午10:39:23
 * @version 1.0.1 备注：
 */
public class LoginActivity extends BaseActivity {
	private Button bt_login;
	private EditText login_workID, login_userName;
	Bundle bundle = null;
	private PreferencesTools service;
	private String workID;
	private String name;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			int result = msg.what;
			if (result == 2) { // 正常
				service.SharedPreferences_Save(workID, name); // 保存工号和姓名
				Intent intent1 = new Intent(getApplicationContext(),
						LotteryActivity.class);
				startActivity(intent1);
				finish();

				Toast.makeText(getApplicationContext(),
						R.string._login_success, Toast.LENGTH_SHORT).show();
			} else if (result == 1) { // 工号或者姓名错误
				Toast.makeText(getApplicationContext(), R.string._login_failed,
						Toast.LENGTH_SHORT).show();
			} else if (result == 0) { // 网络异常
				Toast.makeText(getApplicationContext(),
						R.string._login_netwrong, Toast.LENGTH_SHORT).show();
			}
			super.handleMessage(msg);
		}

	};

	@Override
	public void setContentView() {
		setContentView(R.layout.activity_login);

	}

	@Override
	public void initViews() {
		bundle = getIntent().getExtras();
		bt_login = (Button) findViewById(R.id.bt_login);
		login_workID = (EditText) findViewById(R.id.login_workID);
		login_userName = (EditText) findViewById(R.id.login_Name);

		service = new PreferencesTools(this); // 保存基本信息
		Map<String, String> params = service.getPreferences();
		// login_workID.setText(params.get("workID"));
		login_workID.setText("115490");
		// login_userName.setText(params.get("name"));
		login_userName.setText("张同");
		if (bundle != null) {
			String wordID = bundle.getString("wordID");
			String name = bundle.getString("name");
			login_workID.setText(wordID);
			login_userName.setText(name);
		}
		bt_login.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 登陆
		case R.id.bt_login:
			workID = login_workID.getText().toString().trim();
			name = login_userName.getText().toString().trim();
			if (TextUtils.isEmpty(workID)) {
				Toast.makeText(getApplicationContext(),
						R.string._login_workIdNull, Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(name)) {
				Toast.makeText(getApplicationContext(),
						R.string._login_nameNull, Toast.LENGTH_SHORT).show();
				return;
			}
			// Toast.makeText(getApplicationContext(),
			// "name->"+name+",pwd="+workID, Toast.LENGTH_LONG).show();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						// int result = LoginJson.JudgeInfo(workID, name);
						// int result = LoginJson.loginInfo(
						// Interface_Config.lOGIN_URLS, workID, name);
						int result = LoginJson.getJson(name, workID);
						// System.out.println("url-->"
						// + Interface_Config.lOGIN_URLS + workID + name);
						System.out.println(result);
						Message message = new Message();
						message.what = result;
						handler.sendMessage(message);
					} catch (Exception e) {
						Message message = new Message();
						message.what = 0;
						handler.sendMessage(message);
						e.printStackTrace();
					}

				}
			}).start();
			break;

		}
	}

}
