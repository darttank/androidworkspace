package com.example.brocastreceiverdemo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class Level2Broadcast extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		 System.out.println("省政府发给农民兄弟的5000元钱");
		  abortBroadcast();//发无序广播，此api不生效
	}

}
