package com.example.muldownload;
import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	private EditText path;
	private TextView progress;
	private ProgressBar progressBar;
	private Handler handler = new UIHandler();
	private DownloadService servcie;
	private Button downButton;
	private Button pauseButton;

	private final class UIHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 1:
					int downloaded_size = msg.getData().getInt("size");
					progressBar.setProgress(downloaded_size);
					int result = (int) ((float) downloaded_size / progressBar.getMax() * 100);
					progress.setText(result + "%");
					if (progressBar.getMax() == progressBar.getProgress()) {
						Toast.makeText(getApplicationContext(), "下载完成", Toast.LENGTH_LONG).show();
					}
			}
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		path = (EditText) this.findViewById(R.id.editText);
		progress = (TextView) this.findViewById(R.id.textView);
		progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
		downButton = (Button) this.findViewById(R.id.downButton);
		pauseButton = (Button) this.findViewById(R.id.pauseButton);
		downButton.setOnClickListener(new DownloadButton());
		pauseButton.setOnClickListener(new PauseButton());
	}

	private final class DownloadButton implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			DownloadTask task;
			try {
				task = new DownloadTask(path.getText().toString());
				servcie.isPause = false;
				v.setEnabled(false);
				pauseButton.setEnabled(true);
				new Thread(task).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class PauseButton implements OnClickListener {
		@Override
		public void onClick(View v) {
			servcie.isPause = true;
			v.setEnabled(false);
			downButton.setEnabled(true);
		}
	}

	public void pause(View v) {
	}

	private final class DownloadTask implements Runnable {

		public DownloadTask(String target) throws Exception {
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				File destination = Environment.getExternalStorageDirectory();
				servcie = new DownloadService(target, destination, 3, getApplicationContext());
				progressBar.setMax(servcie.fileSize);
			} else {
				Toast.makeText(getApplicationContext(), "SD卡不存在或写保护!", Toast.LENGTH_LONG).show();
			}
		}

		@Override
		public void run() {
			try {
				servcie.download(new DownloadListener() {

					@Override
					public void onDownload(int downloaded_size) {
						Message message = new Message();
						message.what = 1;
						message.getData().putInt("size", downloaded_size);
						handler.sendMessage(message);
					}

				});
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
