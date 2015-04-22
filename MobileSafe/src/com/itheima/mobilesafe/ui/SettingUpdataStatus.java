package com.itheima.mobilesafe.ui;

import com.itheima.mobilesafe.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingUpdataStatus extends RelativeLayout {
	CheckBox cb_updata_status;
	TextView tv_updata_desc,tv_updata_hint;
	String title;
	String desc_on;
	String desc_off;
	public void Init(Context context){
		View.inflate(context, R.layout.setting_updata_item, this);
		cb_updata_status = (CheckBox) findViewById(R.id.cb_updata_status);
		tv_updata_desc = (TextView) findViewById(R.id.tv_updata_desc);
		tv_updata_hint = (TextView) findViewById(R.id.tv_updata_hint);
		
	}

	@SuppressLint("NewApi")
	public SettingUpdataStatus(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		Init(context);
	}

	public SettingUpdataStatus(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		Init(context);
		/*System.out.println(attrs.getAttributeValue(0));
		System.out.println(attrs.getAttributeValue(1));
		System.out.println(attrs.getAttributeValue(2));
		System.out.println(attrs.getAttributeValue(3));
		System.out.println(attrs.getAttributeValue(4));
		System.out.println(attrs.getAttributeValue(5));
		System.out.println(attrs.getAttributeValue(6));*/
		 title = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.itheima.mobilesafe", "title");
		 desc_on = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.itheima.mobilesafe", "desc_on");
		 desc_off = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.itheima.mobilesafe", "desc_off");
	    
		 tv_updata_hint.setText(title);
		 setUpdataDesc(desc_off);
	
	}

	public SettingUpdataStatus(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Init(context);
	}
	
	public boolean isChecked(){
		return cb_updata_status.isChecked();
	}
	public void setChecked(boolean checked){
		cb_updata_status.setChecked(checked);
	}
	
    public void setUpdataDesc(String text){
    	tv_updata_desc.setText(text);
    }

}
