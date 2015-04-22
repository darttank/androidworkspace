package com.lottery.rotary.mobile.android.json;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.lottery.rotary.mobile.android.constant.Interface_Config;
import com.lottery.rotary.mobile.android.tools.StreamTool;

public class LuckStatesJson {
	/**
	 * 根据员工号获取员工的中奖状态
	 * @param number
	 * @return
	 * @throws Exception
	 */
//	public static String getLuckyStates(String number)
//			throws Exception {
//		String path = Interface_Config.LUCKY_STATES_URL + number;
//		URL url = new URL(path);
//		String result = null;
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET");
//		conn.setConnectTimeout(10000);
//		if (conn.getResponseCode() == 200) {
//			InputStream inputStream = conn.getInputStream();
//			result = StreamTool.inputStream2String(inputStream);
//		}
//	//	System.out.println(result);
//		return result;
//
//	}
}
