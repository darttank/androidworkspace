package com.lottery.rotary.mobile.android.constant;

/**
 * 接口常量
 * 
 * @author Alin
 * @time 2014-12-3上午9:42:24
 * @version 1.0.1
 * @remarks
 */
public class Interface_Config {
	// public static final String IP =
	// "http://114.215.182.189:8088/LuckDrawer/";
	// http://pc-201410241015:8080/LuckDrawer/115400
	public static final String IP = "http://192.168.25.181:8080/LuckDrawer/";

	public static final String LOTTERY_URL = IP
			+ "countluck.action?person_name=";
	public static final String GOD_URL = IP + "luckerdrawer.action";
	public static final String lOGIN_URL = IP + "login.action?person_name=";
	public static final String PROGRAM_URL = IP + "gamestate.action";
	public static final String lOGIN_URLS = IP + "login.action";
	//public static final String LUCKY_STATES_URL = IP
			//+ "luckestatejudge.action?person_number=";
	public static final String PERSON_NUMBER = "&person_number=";
	public static final String ADD_VOTE = IP + "addvote.action?game_id=";
	public static final String CANCEL_VOTE = IP + "cancelvote?game_id=";
	public static final String VOTE_STATE = IP + "votestate?personnumber=";
	public static final String VOTE_ID = "&vote_id=";
	public static final String VOTE_LOTTERY_STATE = IP + "topvote.action";
	public static final String LOGIN_UPDATE_STATE = IP
			+ "luckestatejudge.action?person_number=";

}
