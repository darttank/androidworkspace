package com.example.mydialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
//import android.view.Display;
//import android.view.ViewGroup.LayoutParams;
//import android.view.WindowManager;

public class MyDialog extends Dialog{
	private int height;
	
	public MyDialog(Context context) {
		super(context);
	}

	public MyDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	public MyDialog(Context context, int theme) {
		super(context, theme);
	}
	
	public MyDialog(Context context, int theme, int height) {
		super(context, theme);
		this.height = height;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		WindowManager m = getWindow().getWindowManager();  
//		Display d = m.getDefaultDisplay();  //Ϊ��ȡ��Ļ����  
//
//		//�趨��С
//        LayoutParams params = getWindow().getAttributes();   
//        if(height != 0)
//        	params.height = this.height;
//        else
//        	params.height = LayoutParams.WRAP_CONTENT;   //�߶�����Ϊ��Ļ��0.6    
//        params.width = (int) (d.getWidth() * 0.9);    //�������Ϊ��Ļ��0.95  
//        getWindow().setAttributes((android.view.WindowManager.LayoutParams) params); 
	}
	
	
}
