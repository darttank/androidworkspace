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
		//�����Ļ�Ŀ�Ⱥ͸߶�
         WindowManager wm = getWindowManager();
          int screenHeight = wm.getDefaultDisplay().getHeight();
          int screenWidth =wm.getDefaultDisplay().getWidth();
          System.out.println("��Ļ�ĸߣ�"+screenHeight+"��Ļ�Ŀ�"+screenWidth);
          //��ȡ��null��BitMap,ȡ��ͼƬ�ĸߺͿ�
          BitmapFactory.Options options=new Options();
          options.inJustDecodeBounds=true;
         
         Bitmap bitmap= BitmapFactory.decodeFile("/mnt/sdcard/tupian2.jpg", options);
          int optHeight = options.outHeight;
          int optWidth = options.outWidth;
          System.out.println("ͼƬ�ĸߣ�"+optHeight+"ͼƬ�Ŀ�"+optWidth);
          //�������
          
          int dx = optWidth/screenWidth;
          int dy = optHeight/screenHeight;
          int scale=1;
          if(dx>dy&&dy>1){
        	  System.out.println("ˮƽ��������ű���:"+dx);
        	  scale=dx;
          }
          if(dy>dx&&dx>1){
        	  System.out.println("ˮƽ��������ű���:"+dy);
        	  scale=dy;
          }
          
          //д�뵽�ڴ���ȥ
		options.inSampleSize=scale;
		options.inJustDecodeBounds=false;
		bitmap= BitmapFactory.decodeFile("/mnt/sdcard/tupian2.jpg", options);
		imageview.setImageBitmap(bitmap);
	}
	

}
