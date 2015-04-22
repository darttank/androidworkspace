package com.lottery.rotary.mobile.android.activity;

import com.lottery.rotary.mobile.android.R;

import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;
/**
 * Splash界面，进行数据初始化
 * @author Alin
 * @time 2014-11-7上午10:40:08
 * @version 1.0.1
 * 备注：
 */
public class SplashActivity extends BaseActivity {
	//private TextView splash_version;
	private RelativeLayout splash_rl;
	@Override
	public void setContentView() {
		setContentView(R.layout.activity_splash);
	}

	@Override
	public void initViews() {
		splash_rl = (RelativeLayout) findViewById( R.id.splash_rl);
//		splash_version = (TextView) findViewById( R.id.splash_version);
//		splash_version.setText(getVersion());
		/**
		 *  渐变动画
		 */
		AlphaAnimation animation = new AlphaAnimation(0, 1);
		animation.setDuration(3000);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// 动画结束后，跳转到登陆界面
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		splash_rl.setAnimation(animation);
		
	}

	/**
	 * 获得当前版本号
	 * @return 版本号
	 *//*
	private String getVersion() {
		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(
					getPackageName(), 0);
			return Server_Config.VERSION_NO + packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return Server_Config.VERSION_NO + "1.0.1";
	}*/

	@Override
	public void onClick(View v) {
	}
	

}
