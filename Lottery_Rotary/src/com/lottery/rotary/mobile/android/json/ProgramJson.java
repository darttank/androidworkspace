package com.lottery.rotary.mobile.android.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lottery.rotary.mobile.android.constant.Interface_Config;

public class ProgramJson {
	public static String getProgram() throws Exception {
		String path = Interface_Config.PROGRAM_URL;
		String result = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(path);

		HttpResponse response = client.execute(get);
		int code = response.getStatusLine().getStatusCode();
		if (code == 200) {
			result = EntityUtils.toString(response.getEntity());
			//map = getVoteFromJson(result);
		}
		return result;
	}

	/**
	 * 解析JSON
	 * 
	 * @param json
	 * @return
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static Map<List<String>, List<String>> getProInfoFromJson(String json)
			throws JSONException, Exception {
		List<String> listName = new ArrayList<String>();
		List<String> listInformation = new ArrayList<String>();
		Map<List<String>, List<String>> map = new HashMap<List<String>, List<String>>();
		JSONObject object = new JSONObject(json);

		String GameState = object.getString("GameState");
		System.out.println("GameState->" + GameState);
		JSONObject object2 = new JSONObject(GameState);
		JSONArray nameArray = object2.getJSONArray("game_name:");
		for (int i = 0; i < nameArray.length(); i++) {
			listName.add(nameArray.getString(i));
		}
		JSONArray informationArray = object2.getJSONArray("game_information:");
		for (int i = 0; i < informationArray.length(); i++) {
			listInformation.add(informationArray.getString(i));
		}
		map.put(listName, listInformation);
		System.out.println(map);
		return map;
	}

	/**
	 * 解析JSON
	 * 
	 * @param json
	 * @return
	 * @return
	 * @throws JSONException
	 * @throws Exception
	 */
	public static Map<List<Integer>, List<String>> getProIDFromJson(String json)
			throws JSONException, Exception {
		List<String> listName = new ArrayList<String>();
		List<Integer> listID = new ArrayList<Integer>();
		Map<List<Integer>, List<String>> map = new HashMap<List<Integer>, List<String>>();
		JSONObject object = new JSONObject(json);

		String GameState = object.getString("GameState");
		System.out.println("GameState->" + GameState);
		JSONObject object2 = new JSONObject(GameState);
		JSONArray nameArray = object2.getJSONArray("game_name:");
		for (int i = 0; i < nameArray.length(); i++) {
			listName.add(nameArray.getString(i));
		}
		JSONArray informationArray = object2.getJSONArray("id:");
		for (int i = 0; i < informationArray.length(); i++) {
			listID.add(informationArray.getInt(i));
		}
		map.put(listID, listName);
		System.out.println(map);
		return map;
	}
}
