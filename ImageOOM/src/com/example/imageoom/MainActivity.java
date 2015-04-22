package com.example.imageoom;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends Activity {
	ImageView imageview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageview=(ImageView)findViewById(R.id.imageview);
				
	}
	public void click(View view){
		
		/*Bitmap bm = BitmapFactory.decodeFile("/mnt/sdcard/tupian2.jpg");
		imageview.setImageBitmap(bm);*/
		//获得屏幕的宽度和高度
         WindowManager wm = getWindowManager();
          int screenHeight = wm.getDefaultDisplay().getHeight();
          int screenWidth =wm.getDefaultDisplay().getWidth();
          System.out.println("屏幕的高："+screenHeight+"屏幕的宽："+screenWidth);
          //获取带null的BitMap,取得图片的高和宽
          BitmapFactory.Options options=new Options();
          options.inJustDecodeBounds=true;
         
         Bitmap bitmap= BitmapFactory.decodeFile("/mnt/sdcard/tupian2.jpg", options);
          int optHeight = options.outHeight;
          int optWidth = options.outWidth;
          System.out.println("图片的高："+optHeight+"图片的宽："+optWidth);
          //计算比例
          
          int dx = optWidth/screenWidth;
          int dy = optHeight/screenHeight;
          int scale=1;
          if(dx>dy&&dy>1){
        	  System.out.println("水平方向的缩放比例:"+dx);
        	  scale=dx;
          }
          if(dy>dx&&dx>1){
        	  System.out.println("水平方向的缩放比例:"+dy);
        	  scale=dy;
          }
          
          //写入到内存中去
		options.inSampleSize=scale;
		options.inJustDecodeBounds=false;
		bitmap= BitmapFactory.decodeFile("/mnt/sdcard/tupian2.jpg", options);
		imageview.setImageBitmap(bitmap);
	}
	

}
