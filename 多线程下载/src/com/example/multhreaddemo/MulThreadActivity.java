package com.example.multhreaddemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.multhread.MulThreadDownload.DownloadThread;

import sms.down.R;
import sms.down.R.layout;
import sms.down.R.menu;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MulThreadActivity extends Activity {
	
	ProgressBar pb1,pb2;
	TextView tv1,tv2;
	
	String root = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator;
	 private String downloadFile1 = "http://gongxue.cn/yingyinkuaiche/UploadFiles_9323/201008/2010082909434077.mp3";  
	 private String downloadFile2 = "http://gongxue.cn/yingyinkuaiche/UploadFiles_9323/201008/2010082909434077.mp3";  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mul_thread);
		pb1 = (ProgressBar) findViewById(R.id.progressbar1);
		pb2 = (ProgressBar) findViewById(R.id.progressbar2);
		tv1=(TextView) findViewById(R.id.tv1);
		tv2=(TextView) findViewById(R.id.tv2);
		
		download(downloadFile1,root,pb1,tv1);
		download(downloadFile2,root,pb2,tv2);
		
	}
	public class MyHandlerDemo extends Handler{
		ProgressBar progressBar;
		TextView textview;
		
		public MyHandlerDemo(ProgressBar progressBar, TextView textview){
			this.progressBar = progressBar;
			this.textview = textview;
			
		}
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			
			this.progressBar.setProgress(msg.arg1);
			this.textview.setText(msg.arg1+"%%");
			
			super.handleMessage(msg);
		}
	}

	private void download(String url, String targetPath,
			ProgressBar pb, TextView tv) {
		// TODO Auto-generated method stub
		
		DownloadThread dt = new DownloadThread(url,targetPath,pb,tv);
		dt.start();
		
	}

	public class DownloadThread extends Thread{
		
		String url = "";
		String targetPath = "";
		ProgressBar pb = null;
		TextView tv = null;
		private int size = 0;
		byte buffer[] = new byte[4*1024];
		private int  len =-1;
		int hasDownload = 0;  
		int rate = 0;  
		Message msg = null;  
		MyHandlerDemo myHandler = null;  
		public DownloadThread(String url, String targetPath, ProgressBar pb,
				TextView tv) {
			// TODO Auto-generated constructor stub
			this.url = url;
			this.targetPath = targetPath;
			this.pb = pb;
			this.tv = tv;
			myHandler = new MyHandlerDemo(this.pb, this.tv);
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String targetFileName = this.targetPath+this.url.substring(
				    this.url.lastIndexOf("/")+1,this.url.length());
			File downloadFile = new File(targetFileName);
			
			if(!downloadFile.exists()){
				try {
					downloadFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				URL fileUrl = new URL(this.url);
				HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
				
				size  = conn.getContentLength();
				
				InputStream is = conn.getInputStream();
				
				OutputStream os = new FileOutputStream(targetFileName);
				
				while((len = is.read(buffer))!=-1){
					os.write(buffer);
					
					hasDownload +=len;
					rate = (hasDownload *100/size);
					
					msg = new Message();
					
					msg.arg1 = rate;
					
					myHandler.sendMessage(msg);
					
				}
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}

}
