package com.lottery.rotary.mobile.android.tools;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 存储一些简单的信息
 * 
 * @author LaoJiang
 * 
 */
public class PreferencesTools {
	private Context context;

	public PreferencesTools(Context context) {
		this.context = context;
	}

	/**
	 * 将内容写到文件当中
	 * 
	 * @param 文件名
	 * @param 内容
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void save(String filename, String content) throws Exception {
		FileOutputStream outputStream = context.openFileOutput(filename,
				Context.MODE_PRIVATE);
		outputStream.write(content.getBytes());
		outputStream.close();
	}

	/**
	 * 读取文件内容
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public String read(String filename) throws Exception {
		FileInputStream inputStream = context.openFileInput(filename);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		return new String(data);

	}

	/**
	 * 保存参数
	 * 
	 * @param wordID
	 *            工号
	 * @param name
	 *            年龄
	 */
	public void SharedPreferences_Save(String workID, String name) {
		SharedPreferences preferences = context.getSharedPreferences("itcast",
				Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString("workID", workID);
		editor.putString("name", name);
		editor.commit();
	}

	/**
	 * 获取各项配置参数
	 * 
	 * @return 配置参数，Map
	 */
	public Map<String, String> getPreferences() {
		Map<String, String> params = new HashMap<String, String>();
		SharedPreferences preferences = context.getSharedPreferences("itcast",
				Context.MODE_PRIVATE);
		params.put("workID", preferences.getString("workID", ""));
		params.put("name", preferences.getString("name", ""));
		return params;

	}
}
