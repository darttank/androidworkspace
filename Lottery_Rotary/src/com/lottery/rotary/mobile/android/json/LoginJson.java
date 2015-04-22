package com.lottery.rotary.mobile.android.json;

import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;

import com.lottery.rotary.mobile.android.constant.Interface_Config;
import com.lottery.rotary.mobile.android.tools.StreamTool;

/***
 * 
 * @author LaoJiang
 * 
 */
public class LoginJson {
	/**
	 * 
	 * @param name
	 * @param workID
	 * @return
	 * @throws Exception
	 */
	public static int getJson(String name, String workID) throws Exception {
		String path = Interface_Config.lOGIN_URL
				+ URLEncoder.encode(name, "GB2312")
				+ Interface_Config.PERSON_NUMBER + workID;
		String result = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(path);

		HttpResponse response = client.execute(get);
		int code = response.getStatusLine().getStatusCode();
		if (code == 200) {
			result = EntityUtils.toString(response.getEntity());
			return parseJSONWithStr(result);
		}
		Log.v("result", result);
		return 1;
	}

	/**
	 * 登陆的时候修改中奖的状态
	 * 
	 * @param workID
	 * @throws Exception
	 */
	public static void updateStateToLottery(String workID) throws Exception {
		String path = Interface_Config.LOGIN_UPDATE_STATE + workID;
		String result = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(path);

		HttpResponse response = client.execute(get);
		int code = response.getStatusLine().getStatusCode();
		if (code == 200) {
			result = EntityUtils.toString(response.getEntity());
		}
		System.out.println(result);
	}

	/**
	 * 解析
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static int parseJSON(InputStream inputStream) throws Exception {
		byte[] result = StreamTool.read(inputStream);
		String json = new String(result);
		System.out.println(json);
		JSONObject jsonObject = new JSONObject(json);
		String state = jsonObject.getString("login_state");
		if ("true".equals(state)) {
			return 2;
		}
		if ("luck_state".equals(state)) {
			return 3;
		}
		return 1;
	}

	/**
	 * 解析
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static int parseJSONWithStr(String result) throws Exception {
		// byte[] result = StreamTool.read(inputStream);
		// String json = new String(result);
		// System.out.println(json);
		JSONObject jsonObject = new JSONObject(result);
		String state = jsonObject.getString("login_state");
		if ("true".equals(state)) {
			return 2;
		}
		if ("luck_state".equals(state)) {
			return 3;
		}
		return 1;
	}

}
