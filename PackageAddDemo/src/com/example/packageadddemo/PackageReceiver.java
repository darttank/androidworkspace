package com.example.packageadddemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PackageReceiver extends BroadcastReceiver {
	

	@Override
	public void onReceive(Context content, Intent intent) {
		
		// TODO Auto-generated method stub
		   if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
	             String packageName = intent.getDataString();
	             Log.i("Test","---------------" + packageName);
	             ServiceDemo  sd=new ServiceDemo();
	             content.startService(new Intent(ServiceDemo.ACTION));
	          }
	        
	         if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
	          String packageName = intent.getDataString();
	           Log.i("Test","---------------" + "PACKAGE_REMOVED" + packageName);
	         }
	}

}
