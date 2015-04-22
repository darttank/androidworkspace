package com.example.multhread;

import java.io.File;

import org.w3c.dom.Text;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	TextView tv_progress;
	EditText path;
	ProgressBar progressBar;
	Button downButton,pauseButton;
	DownloadService servcie;  
	private final class UIHandler extends Handler {  
		       @Override  
		       public void handleMessage(Message msg) {  
		           switch (msg.what) {  
		                case 1:  
		                   int downloaded_size = msg.getData().getInt("size");  
		                  progressBar.setProgress(downloaded_size);  
		                   int result = (int) ((float) downloaded_size / progressBar.getMax() * 100);  
		                   tv_progress.setText(result + "%");  
	                   if (progressBar.getMax() == progressBar.getProgress()) {  
		                       Toast.makeText(getApplicationContext(), "下载完成", Toast.LENGTH_LONG).show();  
		                   }  
		            }  
		       }  
		   }  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
		path = (EditText) this.findViewById(R.id.et1);
		tv_progress = (TextView) this.findViewById(R.id.tv_proress);
		downButton = (Button) this.findViewById(R.id.btn_down);
	    pauseButton = (Button) this.findViewById(R.id.btn_pause);
	    downButton.setOnClickListener(new DownloadButton());
	    downButton.setOnClickListener(new PauseButton());
	    
	}

	class DownloadButton implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DownloadTask task;
			
			task = new DownloadTask(path.getText().toString());
			v.setEnabled(false);
			pauseButton.setEnabled(true);
			
			
		}
		
	}
	class PauseButton implements View.OnClickListener{
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			v.setEnabled(true);
			downButton.setEnabled(true);
		}
		
	}
	
	public void pause(View view){
		
	}
	
	class DownloadTask implements Runnable{

		public DownloadTask(String string) {
			// TODO Auto-generated constructor stub
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				File destination = Environment.getExternalStorageDirectory();
				
			}else{
				  Toast.makeText(getApplicationContext(), "SD卡不存在或写保护!", Toast.LENGTH_LONG).show();  
			}
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
