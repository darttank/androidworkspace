package com.example.afinaldemo;

import java.io.File;

import net.tsz.afinal.*;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FinalActivity {
	
	@ViewInject(id=R.id.img) ImageView img;
	 TextView textView;
	 Button btn;
	FinalBitmap finalBitMap=null;
	FinalHttp fh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView=(TextView) findViewById(R.id.text);
		btn=(Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/qq.apk";
				File f = new File(apkPath);
				if (f.exists()) {
					f.delete();
				}
				fh=new FinalHttp();
				fh.download("http://gdown.baidu.com/data/wisegame/4ae6d2d7378e6cdf/QQ_122.apk",apkPath, 
						new AjaxCallBack<File>() {
			       @Override
					public void onStart() {
						super.onStart();
						Toast.makeText(getApplicationContext(), "开始下载", Toast.LENGTH_SHORT).show();
					}
					@SuppressLint("DefaultLocale")
					@Override
					public void onLoading(long count, long current) {
						super.onLoading(count, current);
						int progress=0;
						if (current != count && current != 0) {
							progress = (int) (current / (float) count * 100);
						} else {
							progress = 100;
						}
						textView.setText("进度："+progress+"%");
					}
					@Override
					public void onSuccess(File t) {
						super.onSuccess(t);
						Toast.makeText(getApplicationContext(), "下载完成", Toast.LENGTH_SHORT).show();
						textView.setText(t==null?"null":t.getAbsoluteFile().toString());
					}
					@Override
					public void onFailure(Throwable t, int errorNo,String strMsg) {
						super.onFailure(t, errorNo, strMsg);
						Toast.makeText(getApplicationContext(), "下载失败", Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
		
		finalBitMap=FinalBitmap.create(MainActivity.this);
		
		finalBitMap.display(img, "http://ww3.sinaimg.cn/large/4b91f9d5gw1ejisljbsxaj20lb0bzgnc.jpg");
		
	}
	

}
