package com.lottery.rotary.mobile.android.json;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lottery.rotary.mobile.android.constant.Interface_Config;
import com.lottery.rotary.mobile.android.tools.StreamTool;

/**
 * 投票工具类
 * 
 * @author Alin
 * @time 2014-11-25上午9:56:15
 * @version 1.0.1 备注：
 */
public class VoteJson {
	/**
	 * 投票类JSON工具
	 * 
	 * @param pathStr
	 *            JSON地址
	 * @param gameId
	 *            界面id
	 * @param vote_id
	 *            员工号
	 * @return 返回的结果
	 * @throws Exception
	 */
	public static String vote(String pathStr, String gameId, String vote_id)
			throws Exception {
		String path = pathStr + gameId + Interface_Config.VOTE_ID + vote_id;
		URL url = new URL(path);
		String result = null;
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(10000);
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			result = StreamTool.inputStream2String(inputStream);
		}
		System.out.println("vote->" + result);
		return result;
	}

	/**
	 * 获得投票的状态
	 * 
	 * @param pathStr
	 * @param personnumber
	 * @return
	 * @throws Exception
	 */
	public static String voteState(String pathStr, String personnumber)
			throws Exception {
		String path = pathStr + personnumber;
		URL url = new URL(path);
		String result = null;
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(10000);
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			result = StreamTool.inputStream2String(inputStream);
		}
		System.out.println("voteState->" + result);
		return result;
	}

	/**
	 * 获得投票中奖的状态
	 * 
	 * @param pathStr
	 * @param personnumber
	 * @return
	 * @throws Exception
	 */
	public static String voteWinState(String pathStr) throws Exception {
		String path = pathStr;
		URL url = new URL(path);
		String result = null;
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(10000);
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			result = StreamTool.inputStream2String(inputStream);
		}
		System.out.println("voteWinState-->" + result);
		return result;
	}

	/**
	 * 解析JSON
	 * 
	 * @param json
	 * @param stateStr
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static List<Integer> getVoteFromJson(String json, String stateStr)
			throws JSONException, Exception {
		List<Integer> list = new ArrayList<Integer>();
		JSONObject newJsonObject = new JSONObject(json);
		JSONArray array = newJsonObject.getJSONArray("GameState");
		for (int i = 0; i < array.length(); i++) {
		//	System.out.println(array.getInt(i));
			list.add(array.getInt(i));
		}
		return list;
	}
}
