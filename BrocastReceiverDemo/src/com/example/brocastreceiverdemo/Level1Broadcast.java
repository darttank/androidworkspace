package com.example.brocastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Level1Broadcast extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		//  abortBroadcast();//������㲥����api����Ч
          System.out.println("���뷢��ũ���ֵܵ�10000ԪǮ");
          
	}

}
