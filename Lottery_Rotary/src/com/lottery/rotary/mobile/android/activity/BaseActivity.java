package com.lottery.rotary.mobile.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;

/**
 * 基类
 * 
 * @author Alin
 * @time 2014-11-7上午10:39:08
 * @version 1.0.1 备注：
 */
public abstract class BaseActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView();
		initViews();
	}

	/**
	 * 填充布局
	 */
	public abstract void setContentView();

	/**
	 * 初始化控件
	 */
	public abstract void initViews();

	/**
	 * 重写后退按钮，不让其有操作
	 */
	/*
	 * @Override public void onBackPressed() {
	 * 
	 * }
	 */
}
