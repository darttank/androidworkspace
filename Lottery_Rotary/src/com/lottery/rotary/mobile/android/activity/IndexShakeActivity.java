package com.lottery.rotary.mobile.android.activity;

import java.util.Random;

import com.lottery.rotary.mobile.android.R;
import com.lottery.rotary.mobile.android.activity.ShakeListenerUtils.OnShakeListener;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class IndexShakeActivity extends Activity {

	private Vibrator vibrator;// 震动
	/** 摇之�?遥之�?,隐藏�? */
	private ImageView imView, imcount, imgnot;
	/** 监听 */
	private ShakeListenerUtils shakeListener;
	private Button btnBack;

	private int icon[] = { R.drawable.image_left, R.drawable.image_middle,
			R.drawable.image_right };

	// private int icoSotp[] = { R.drawable.lottery_result,
	// R.drawable.lottery_head_2
	// ,R.drawable.lottery_nothing,R.drawable.lottery_something};

	private int icoSotp[] = { R.drawable.lottery_result };

	private int index = 0;

	private int randomC = 0;

	private Random random;

	private boolean flag = true;

	private int a = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shake);
		init();
	}

	public void init() {
		vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

		/*imView = (ImageView) this.findViewById(R.id.imgmiddle);
		imcount = (ImageView) this.findViewById(R.id.imgtit);
		imgnot = (ImageView) this.findViewById(R.id.imgnoth);*/
		// random=new Random();

		shakeListener = new ShakeListenerUtils(this);
		shakeListener.setOnShake(onShake);
		btnBack = (Button) this.findViewById(R.id.shakeback);
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				IndexShakeActivity.this.finish();

			}
		});

	}

	/** 重力感应 */
	private OnShakeListener onShake = new OnShakeListener() {

		@Override
		public void onShake() {

			if (flag == true) {
				imgnot.setVisibility(View.GONE);

				startVibrator();

				shakeListener.stop();

				handler.sendEmptyMessageDelayed(1, 100);

				handler.sendEmptyMessageDelayed(2, 4000);

				flag = false;
       
			}

			// randomC=random.nextInt(6);
			// Log.e("--", "---"+randomC);

		}
	};

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 1) {
				if (index < icon.length - 1) {
					index++;
				} else {
					index = 0;
				}
				imView.setBackgroundResource(icon[index]);
				handler.sendEmptyMessageDelayed(1, 170); // 控制 鸡蛋摇奖的速度

			} else {
				imView.setBackgroundResource(icoSotp[0]);
				// imcount.setBackgroundResource(icoSotp[1]);
				handler.removeMessages(1);
				AlertDialog dialog = new AlertDialog.Builder(
						IndexShakeActivity.this)
						.setTitle("中奖提示")
						.setMessage("你好")
						.setPositiveButton(
								"继续",
								new android.content.DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										vibrator.cancel();
										shakeListener.start();
										flag = true;
										// yaoyao.setVisibility(View.VISIBLE);
									}
								}).create();
				dialog.show();
				dialog.setCanceledOnTouchOutside(false);
				shakeListener.start();

				/*
				 * //AlertDialog.Builder builder = new
				 * Builder(IndexShakeActivity.this); builder.setTitle("中奖提示");
				 * builder.setMessage(lucky); builder.setMessage("你好");
				 * builder.setPositiveButton("继续", new
				 * android.content.DialogInterface.OnClickListener() {
				 * 
				 * @Override public void onClick(DialogInterface dialog, int
				 * which) { vibrator.cancel(); shakeListener.start(); flag=true;
				 * yaoyao.setVisibility(View.VISIBLE); } });
				 * builder.create().show(); shakeListener.start();
				 */
				/*
				 * flag=true; if(a==1){ shakeListener.start(); }
				 */
				// 把这句代码放入到对话框中去
				/*
				 * if(randomC==5){ imgnot.setBackgroundResource(icoSotp[3]);
				 * imgnot.setVisibility(View.VISIBLE); }else{
				 * imgnot.setBackgroundResource(icoSotp[2]);
				 * imgnot.setVisibility(View.VISIBLE); }
				 */

			}
		}

	};

	/**
	 * 播放振动效果
	 */
	public void startVibrator() {
		vibrator.vibrate(new long[] { 500, 300, 500, 300 }, -1);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
