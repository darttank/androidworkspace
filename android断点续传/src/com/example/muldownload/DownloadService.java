package com.example.muldownload;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DownloadService {
	public static final String TAG = "tag";
	/* ���ڲ�ѯ���ݿ� */
	private DBHelper dbHelper;
	/* Ҫ���ص��ļ���С */
	public int fileSize;
	/* ÿ���߳���Ҫ���ص������� */
	private int block;
	/* �����ļ���Ŀ¼ */
	private File savedFile;
	/* ���ص�ַ */
	private String path;
	/* �Ƿ�ֹͣ���� */
	public boolean isPause;
	/* �߳��� */
	private MultiThreadDownload[] threads;
	/* ���߳��Ѿ����ص������� */
	private Map<Integer, Integer> downloadedLength = new ConcurrentHashMap<Integer, Integer>();

	public DownloadService(String target, File destination, int thread_size, Context context) throws Exception {
		dbHelper = new DBHelper(context);
		this.threads = new MultiThreadDownload[thread_size];
		this.path = target;
		URL url = new URL(target);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("server no response!");
		}

		fileSize = conn.getContentLength();
		if (fileSize <= 0) {
			throw new RuntimeException("file is incorrect!");
		}
		String fileName = getFileName(conn);
		if (!destination.exists()) destination.mkdirs();
		// ����һ��ͬ����С���ļ�
		this.savedFile = new File(destination, fileName);
		RandomAccessFile doOut = new RandomAccessFile(savedFile, "rwd");
		doOut.setLength(fileSize);
		doOut.close();
		conn.disconnect();

		// ����ÿ���߳���Ҫ���ص����ݳ���
		this.block = fileSize % thread_size == 0 ? fileSize / thread_size : fileSize / thread_size + 1;
		// ��ѯ�Ѿ����صļ�¼
		downloadedLength = this.getDownloadedLength(path);
	}

	private Map<Integer, Integer> getDownloadedLength(String path) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String sql = "SELECT threadId,downLength FROM fileDownloading WHERE downPath=?";
		Cursor cursor = db.rawQuery(sql, new String[] { path });
		Map<Integer, Integer> data = new HashMap<Integer, Integer>();
		while (cursor.moveToNext()) {
			data.put(cursor.getInt(0), cursor.getInt(1));
		}
		db.close();
		return data;
	}

	private String getFileName(HttpURLConnection conn) {
		String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
		if (fileName == null || "".equals(fileName.trim())) {
			String content_disposition = null;
			for (Entry<String, List<String>> entry : conn.getHeaderFields().entrySet()) {
				if ("content-disposition".equalsIgnoreCase(entry.getKey())) {
					content_disposition = entry.getValue().toString();
				}
			}
			try {
				Matcher matcher = Pattern.compile(".*filename=(.*)").matcher(content_disposition);
				if (matcher.find()) fileName = matcher.group(1);
			} catch (Exception e) {
				fileName = UUID.randomUUID().toString() + ".tmp"; // Ĭ����
			}
		}
		return fileName;
	}

	public void download(DownloadListener listener) throws Exception {
		this.deleteDownloading(); // ��ɾ���ϴεļ�¼,���������
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new MultiThreadDownload(i, savedFile, block, path, downloadedLength.get(i), this);
			new Thread(threads[i]).start();
		}
		this.saveDownloading(threads);

		while (!isFinish(threads)) {
			Thread.sleep(900);
			if (listener != null) listener.onDownload(getDownloadedSize(threads));
			this.updateDownloading(threads);
		}
		if (!this.isPause) this.deleteDownloading();// �������֮��ɾ���������ؼ�¼
	}

	private void saveDownloading(MultiThreadDownload[] threads) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
			db.beginTransaction();
			for (MultiThreadDownload thread : threads) {
				String sql = "INSERT INTO fileDownloading(downPath,threadId,downLength) values(?,?,?)";
				db.execSQL(sql, new Object[] { path, thread.id, 0 });
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
	}

	private void deleteDownloading() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String sql = "DELETE FROM fileDownloading WHERE downPath=?";
		db.execSQL(sql, new Object[] { path });
		db.close();
	}

	private void updateDownloading(MultiThreadDownload[] threads) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
			db.beginTransaction();
			for (MultiThreadDownload thread : threads) {
				String sql = "UPDATE fileDownloading SET downLength=? WHERE threadId=? AND downPath=?";
				db.execSQL(sql, new String[] { thread.currentDownloadSize + "", thread.id + "", path });
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
	}

	private int getDownloadedSize(MultiThreadDownload[] threads) {
		int sum = 0;
		for (int len = threads.length, i = 0; i < len; i++) {
			sum += threads[i].currentDownloadSize;
		}
		return sum;
	}

	private boolean isFinish(MultiThreadDownload[] threads) {
		try {
			for (int len = threads.length, i = 0; i < len; i++) {
				if (!threads[i].finished) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
