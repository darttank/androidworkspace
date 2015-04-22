package com.example.packageadddemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Android Service 
 * 
 * @author dev
 * 
 */
public class ServiceDemo extends Service {
	private static final String TAG = "ServiceDemo" ;
	public static final String ACTION = "com.example.packageadddemo.ServiceDemo";
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "ServiceDemo onBind");
		return null;
	}
	
	@Override
	public void onCreate() {
		Log.i(TAG, "ServiceDemo onCreate");
		super.onCreate();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		Log.i(TAG, "ServiceDemo onStart");
		super.onStart(intent, startId);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "ServiceDemo onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
}
