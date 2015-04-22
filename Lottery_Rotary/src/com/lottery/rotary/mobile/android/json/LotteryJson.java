package com.lottery.rotary.mobile.android.json;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import com.lottery.rotary.mobile.android.constant.Interface_Config;
import com.lottery.rotary.mobile.android.tools.StreamTool;

public class LotteryJson {

	/**
	 * 获得获奖状态
	 * 
	 * @param path
	 *            传入的地址
	 * @param person_name
	 *            员工姓名
	 * @param person_number
	 *            员工号
	 * @return 获奖状态
	 * @throws Exception
	 */

	public static String getLuckyStates(String name, String number)
			throws Exception {
		String path = Interface_Config.LOTTERY_URL
				+ URLEncoder.encode(name, "GB2312")
				+ Interface_Config.PERSON_NUMBER + number;
		System.out.println("path-->" + path);
		URL url = new URL(path);
		String result = null;
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(10000);
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			result = StreamTool.inputStream2String(inputStream);
		}
		// System.out.println(result);
		return result;

	}

	/**
	 * 解析中奖状态
	 * 
	 * @param json
	 *            JSON格式的数据
	 * @return 中奖状态
	 * @throws JSONException
	 * @throws Exception
	 */
	public static String getLuckyFromJson(String json, String stateStr)
			throws JSONException, Exception {
		JSONObject newJsonObject = new JSONObject(json);
		String states = newJsonObject.getString(stateStr);
		// System.out.println(states);
		return states;
	}
}
