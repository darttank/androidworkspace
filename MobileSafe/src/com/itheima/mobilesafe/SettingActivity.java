package com.itheima.mobilesafe;

import com.itheima.mobilesafe.ui.SettingUpdataStatus;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SettingActivity extends Activity {
	SettingUpdataStatus rl_setting_updata;
	SharedPreferences sp;
	Editor edit;
	boolean isClick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_activity);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		rl_setting_updata = (SettingUpdataStatus) findViewById(R.id.rl_setting_updata);
		edit = sp.edit();
		
		if(sp.getBoolean("updata", false)){
			rl_setting_updata.setChecked(true);
			rl_setting_updata.setUpdataDesc("开启");
		}else{
			rl_setting_updata.setChecked(false);
			rl_setting_updata.setUpdataDesc("关闭");
		}
		
		rl_setting_updata.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (rl_setting_updata.isChecked()) {
					rl_setting_updata.setChecked(false);
					rl_setting_updata.setUpdataDesc("关闭");
					edit.putBoolean("updata", false);
				} else {
					rl_setting_updata.setChecked(true);
					rl_setting_updata.setUpdataDesc("开启");
					edit.putBoolean("updata", true);
				}
				edit.commit();

			}
		});

	}
}
