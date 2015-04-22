package com.example.tweenanima;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
    ImageView imageview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageview = (ImageView) findViewById(R.id.imageview);
		
	}
	
    public void alph(View view){
	  AlphaAnimation aa=new AlphaAnimation(0.0f, 1.0f);
	  aa.setDuration(2000);
	  aa.setRepeatCount(1);
	  aa.setRepeatMode(Animation.REVERSE);
	  imageview.startAnimation(aa);
	   
     }
    public void scale(View view){
    	ScaleAnimation sa = new ScaleAnimation(0.1f, 2.0f, 0.1f, 2.0f, Animation.RELATIVE_TO_SELF, 
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(2000);
		sa.setRepeatCount(1);
		sa.setRepeatMode(Animation.REVERSE);
		imageview.startAnimation(sa);
 	   
    }
    public void trans(View view){
  	  TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f,
  			  Animation.RELATIVE_TO_PARENT,0.5f,
  			  Animation.RELATIVE_TO_PARENT,0.0f,
  			  Animation.RELATIVE_TO_PARENT,0.0f
  			  );
  	  ta.setDuration(2000);
  	  ta.setRepeatCount(1);
  	  ta.setRepeatMode(Animation.REVERSE);
  	  imageview.startAnimation(ta);
 	   
    }
    public void rotate(View view){
    	RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		ra.setDuration(2000);
		ra.setRepeatCount(1);
		ra.setRepeatMode(Animation.REVERSE);
		imageview.startAnimation(ra);
 	   
    }
    

}
