package com.itheima.mobilesafe;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.itheima.utils.StreamTools;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity {
	protected static final int ENTER_HOME = 0;
	protected static final int SHOW_UPDATE_DIALOG = 1;
	protected static final int URL_ERROR = 2;
	protected static final int NETWORK_ERROR = 3;
	protected static final int JSON_ERROR = 4;
	TextView tv_splash_version;
	String version = null;
	String description = null;
	String apkurl = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//全屏显示不会滑出任务栏
		requestWindowFeature(Window.FEATURE_NO_TITLE); //设置 无title bar，全屏，但是会滑动出来任务栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); //有title bar ，不会滑出任务栏
		
		setContentView(R.layout.activity_splash);
		tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
		tv_splash_version.setText("版本号：" + getVersionName());

		// 检查更新
		checkUpdate();
		
		//动画效果
		AlphaAnimation aa=new AlphaAnimation(0.1f,1.0f);
		aa.setDuration(1000);
		findViewById(R.id.splash_anim).startAnimation(aa);
	}

	private Handler hanlder = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ENTER_HOME:
				//进入主界面
                enterHome();
				break;
			case SHOW_UPDATE_DIALOG:
				//显示升级对话框
				 showUpdateDialog();
				break;
			case URL_ERROR:
                Toast.makeText(SplashActivity.this, "URL错误", 0).show();
                enterHome();
				break;
			case NETWORK_ERROR:
				 Toast.makeText(SplashActivity.this, "网络错误", 0).show();
				break;
			case JSON_ERROR:
				 Toast.makeText(SplashActivity.this, "Json错误", 0).show();
				break;
		    default:
		    	break;

			}

		}

	

		

	};
	
	//显示升级对话框
	public void showUpdateDialog() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("提示升级");
		builder.setMessage(description);
		builder.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		
		builder.setNegativeButton("取消", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				arg0.dismiss();
				enterHome();//进入主界面
			}
		});
		builder.show();
	}

	public void enterHome() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
		startActivity(intent);
		
	}
	
	public void checkUpdate() {
           
		new Thread() {
			
			@Override
			public void run() {
				long startTime =System.currentTimeMillis();
				Message msg = Message.obtain();

				try {
					URL url = new URL(getString(R.string.serverurl));
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setReadTimeout(4000);
					conn.setRequestMethod("GET");

					int resposeCode = conn.getResponseCode();

					if (resposeCode == 200) {
						InputStream inputstream = conn.getInputStream();
						String result = StreamTools.readFromStream(inputstream);
						Log.i("Tag", result);
                        
						//刚刚忘记吧result封装到JSONObject里面去了
						JSONObject obj = new JSONObject(result);

						version = (String) obj.get("version");
						description = (String) obj.get("description");
						apkurl = (String) obj.get("apkurl");

						if (getVersionName().equals(version)) {
							msg.what = ENTER_HOME;

						} else {
							msg.what = SHOW_UPDATE_DIALOG;
						}

					}

				} catch (MalformedURLException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.what = URL_ERROR;
				} catch (IOException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.what = NETWORK_ERROR;
				} catch (JSONException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.what = JSON_ERROR;
				} finally {
					//发送hanlder之前先判断是否运行有2秒
					long endTime =System.currentTimeMillis();
					long dTime = endTime - startTime;
					if( dTime <2000){
						try {
							Thread.sleep(2000 - dTime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					hanlder.sendMessage(msg);
				}
			}

		}.start();

	}
     //finish Activity 在 onStop之前 
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

	public String getVersionName() {
		PackageManager pm = getPackageManager();

		try {
			PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
			return pi.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}

	}

}
