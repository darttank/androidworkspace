package com.example.notificationdemo;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.style.IconMarginSpan;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View view){
		NotificationManager nm=(NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
		Notification notification=new Notification(R.drawable.ic_launcher, "我是一条消息", System.currentTimeMillis());
		notification.flags=Notification.FLAG_AUTO_CANCEL;
		Intent intent=new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:110"));
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
		notification.setLatestEventInfo(this, "标题", "内容", contentIntent);
		nm.notify(0, notification);
	}
	
	
}
