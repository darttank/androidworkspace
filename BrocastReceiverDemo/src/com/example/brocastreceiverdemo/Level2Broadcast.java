package com.example.brocastreceiverdemo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class Level2Broadcast extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		 System.out.println("ʡ��������ũ���ֵܵ�5000ԪǮ");
		  abortBroadcast();//������㲥����api����Ч
	}

}
