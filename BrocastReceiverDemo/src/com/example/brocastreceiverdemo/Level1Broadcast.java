package com.example.brocastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Level1Broadcast extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		//  abortBroadcast();//发无序广播，此api不生效
          System.out.println("中央发给农民兄弟的10000元钱");
          
	}

}
