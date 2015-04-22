package com.example.dialogdemo2;




import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


/**
 * 鏃堕棿鏃ユ湡dialog
 * 
 * @author zhangxingfeng
 * 
 */
public class DialogLogin extends Dialog implements OnClickListener {
	// 纭鎸夐挳
	private Button okButton, noButton;
	private TextView dialog_text,dialog_text1;
	Activity context;
	private String Warmprompt,Warmprompt1;
	private int changer;
	Intent intent;
	private String url;

	public ExtDialog(Activity context, String Warmprompt, String Warmprompt1,String url) {
		super(context);
		this.context = context;
		this.Warmprompt = Warmprompt;
		this.Warmprompt1 = Warmprompt1;
		this.url = url;
	}

	/**
	 * 鍒濆鍖栨椂闂�鑾峰彇褰撳墠绯荤粺鏃堕棿
	 */
	public void init() {
		dialog_text.setText(Warmprompt);
		dialog_text1.setText(Warmprompt1);
	}

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/** 鏄剧ずdialogin鍥惧眰 */
		setContentView(R.layout.dialogin);
		WindowManager m = context.getWindowManager();
		Display d = m.getDefaultDisplay(); // 鑾峰彇灞忓箷瀹姐�楂樼敤
		WindowManager.LayoutParams p = getWindow().getAttributes(); // 鑾峰彇瀵硅瘽妗嗗綋鍓嶇殑鍙傛暟鍊�
		Window dialogWindow = this.getWindow();
		dialogWindow.setGravity(Gravity.CENTER);
		p.width = (int) (d.getWidth() * 0.8);
		dialogWindow.setAttributes(p);
		dialog_text = (TextView) findViewById(R.id.dialog_text);
		dialog_text1 = (TextView) findViewById(R.id.dialog_text1);
		/** dialog涓殑纭鎸夐挳 */
		okButton = (Button) findViewById(R.id.okButton);
		noButton = (Button) findViewById(R.id.noButton);
		/** 鐩戝惉纭鎸夐挳 */
		okButton.setOnClickListener(this);
		noButton.setOnClickListener(this);
		init();
		// 鑾峰彇DatePicker瀵硅薄
	}

	/**
	 * 鐩戝惉鏃ユ湡纭畾鎸夐敭
	 */
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.okButton:
			/** 鍏抽棴dialog */
			//Log.i("鐐瑰嚮纭畾", "纭畾");
			Intent intent = new Intent(context,UpdateService.class);
			intent.putExtra("Key_App_Name", "瀹夋窐鎯�);
			intent.putExtra("Key_Down_Url",url);						
			context.startService(intent);
			cancel();
			break;
		case R.id.noButton:
			/** 鍏抽棴dialog */
			cancel();
			break;
		default:
			break;
		}
	}

	public int getchanger() {
		return changer;
	}
}
