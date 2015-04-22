package com.example.frameanimation;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends Activity {
    ImageView imageview;
    AnimationDrawable animation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageview = (ImageView) findViewById(R.id.iamgeview);
		//获取动画资源
	    imageview.setBackgroundResource(R.drawable.girl);
		//开启动画
	     animation = (AnimationDrawable) imageview.getBackground();
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			animation.start();
			return true;
		}
		return super.onTouchEvent(event);
	}
	
	
}
