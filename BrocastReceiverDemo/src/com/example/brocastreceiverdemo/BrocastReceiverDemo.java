package com.example.brocastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BrocastReceiverDemo extends BroadcastReceiver{
	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		String phoneNum=getResultData();
		System.out.println("打电话了："+phoneNum);
		
	}


}

