package com.example.brocastreceiverdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity3 extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
	}
	//��������㲥--��������
	public void click(View view){
		Intent intent=new Intent();
		intent.setAction("com.example.bro");
		sendBroadcast(intent);
		
	}
	//��������㲥--�����ļ�
     public void click2(View view){
    	Intent intent=new Intent();
 		intent.setAction("com.example.bro");
 	    sendOrderedBroadcast(intent, null, null, null, 0, "��ũ���ֵܵ�10000Ԫ", null);
		
	}


}
