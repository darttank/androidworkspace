package com.example.muldownload;


import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;

public final class MultiThreadDownload implements Runnable {
	public int id;
	private RandomAccessFile savedFile;
	private String path;
	/* ��ǰ�������� */
	public int currentDownloadSize = 0;
	/* ����״̬ */
	public boolean finished;
	/* ���ڼ�������״̬ */
	private final DownloadService downloadService;
	/* �߳������������ʼ�� */
	public int start;
	/* �߳���������Ľ����� */
	private int end;

	public MultiThreadDownload(int id, File savedFile, int block, String path, Integer downlength, DownloadService downloadService) throws Exception {
		this.id = id;
		this.path = path;
		if (downlength != null) this.currentDownloadSize = downlength;
		this.savedFile = new RandomAccessFile(savedFile, "rwd");
		this.downloadService = downloadService;
		start = id * block + currentDownloadSize;
		end = (id + 1) * block;
	}

	@Override
	public void run() {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Range", "bytes=" + start + "-" + end); // ���û�ȡ���ݵķ�Χ

			InputStream in = conn.getInputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			savedFile.seek(start);
			while (!downloadService.isPause && (len = in.read(buffer)) != -1) {
				savedFile.write(buffer, 0, len);
				currentDownloadSize += len;
			}
			savedFile.close();
			in.close();
			conn.disconnect();
			if (!downloadService.isPause) Log.i(DownloadService.TAG, "Thread " + (this.id + 1) + "finished");
			finished = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("File downloading error!");
		}
	}
}
