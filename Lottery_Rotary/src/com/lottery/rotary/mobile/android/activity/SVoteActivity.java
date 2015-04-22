package com.lottery.rotary.mobile.android.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lottery.rotary.mobile.android.R;
import com.lottery.rotary.mobile.android.adapter.VoteAdapter;
import com.lottery.rotary.mobile.android.constant.Interface_Config;
import com.lottery.rotary.mobile.android.constant.Server_Config;
import com.lottery.rotary.mobile.android.json.LotteryJson;
import com.lottery.rotary.mobile.android.json.LuckStatesJson;
import com.lottery.rotary.mobile.android.json.VoteJson;
import com.slidingmenu.lib.SlidingMenu;

/**
 * 投票界面
 * 
 * @author Alin
 * @time 2014-11-24下午2:41:15
 * @version 1.0.1 备注：
 */
public class SVoteActivity extends BaseActivity {
	private ListView vote_list;
	private ImageView toLottery;
	SharedPreferences sp = null;
	private String workID = null;
	private String name = null;
	private String luckyStates = null;
	private String voteWinState = null;
	private ImageView personal_center;
	private TextView person_centre_name, person_centre_workID,
			person_centre_luckstate, vote_state;
	private LinearLayout personal_logout, personal_person_centre_quit;

	private SlidingMenu menu; // 侧滑菜单

	@Override
	public void setContentView() {
		setContentView(R.layout.activity_votes);
		sp = getSharedPreferences("itcast", MODE_PRIVATE);
		name = sp.getString("name", "");
		workID = sp.getString("workID", "");
		System.out.println("workid=" + workID + ",name=" + name);

		/**
		 * 加载界面的同时，获得中奖状态
		 */
//		new Thread() {
//			public void run() {
//				try {
//					String result = LuckStatesJson.getLuckyStates(workID);
//					luckyStates = LotteryJson.getLuckyFromJson(result,
//							Server_Config.LUCY_STATE);
//					sp.edit().putString("luck_state", luckyStates).commit();
//					System.out.println("luckyStates->" + luckyStates);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			};
//		}.start();
//		getVoteState();
//		getVoteWinState();
	}

	@Override
	public void initViews() {
		/**************** 初始化侧滑菜单 ******************/
		menu = new SlidingMenu(getApplicationContext());
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.attachToActivity(SVoteActivity.this, SlidingMenu.SLIDING_CONTENT);
		menu.setBehindOffset(170);
		menu.setFadeDegree(0.35f);
		menu.setMenu(R.layout.personal_center);

		vote_list = (ListView) findViewById(R.id.vote_list);
		toLottery = (ImageView) findViewById(R.id.toLottery);
		personal_center = (ImageView) findViewById(R.id.personal_center);
		person_centre_name = (TextView) findViewById(R.id.person_centre_name);
		person_centre_workID = (TextView) findViewById(R.id.person_centre_workID);
		person_centre_luckstate = (TextView) findViewById(R.id.person_centre_luckstate);
		vote_state = (TextView) findViewById(R.id.vote_state);
		personal_logout = (LinearLayout) findViewById(R.id.personal_logout);
		personal_person_centre_quit = (LinearLayout) findViewById(R.id.personal_person_centre_quit);
		person_centre_name.setText(name);
		person_centre_workID.setText(workID);
		person_centre_luckstate.setText("未中奖");
		if (voteWinState == null) {
			vote_state.setText("未中奖");
		}

		vote_list.setAdapter(new VoteAdapter(getApplicationContext()));
		toLottery.setOnClickListener(this);
		personal_center.setOnClickListener(this);
		personal_logout.setOnClickListener(this);
		personal_person_centre_quit.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.toLottery:
			Intent intent = new Intent(SVoteActivity.this,
					LotteryActivity.class);
			startActivity(intent);
			finish();
			break;
		// 打开个人中心
		case R.id.personal_center:
			menu.toggle();
			break;
		// 点击，摇奖
		case R.id.personal_person_centre_quit:
			System.exit(0);
			break;
		// 点击，摇奖
		case R.id.personal_logout:
			Intent intent1 = new Intent(getApplicationContext(),
					LoginActivity.class);
			startActivity(intent1);
			finish();
			break;

		}
	}

	/**
	 * 得到投票状态
	 */
	private void getVoteState() {
		new Thread() {
			public void run() {
				try {
					String voteState = VoteJson
							.voteState(
									Interface_Config.VOTE_STATE,
									getSharedPreferences("itcast",
											Context.MODE_PRIVATE).getString(
											"workID", ""));
					// String voteState = VoteJson.voteState(
					// Interface_Config.VOTE_STATE, "115404");
					System.out.println(voteState);
					List<Integer> list = VoteJson.getVoteFromJson(voteState,
							"GameState");
					Editor edit = getSharedPreferences("item",
							Context.MODE_PRIVATE).edit();
					for (int i = 0; i < 12; i++) {
						edit.putInt((i + 1) + "", 0);
					}
					for (int i = 0; i < list.size(); i++) {
						edit.putInt(list.get(i) + "", 1);
					}
					edit.commit();

				} catch (Exception e) {
					e.printStackTrace();
				}
			};

		}.start();
	}

	/**
	 * 得到投票中奖状态
	 */
	private void getVoteWinState() {
		new Thread() {

			public void run() {
				try {
					voteWinState = VoteJson
							.voteWinState(Interface_Config.VOTE_LOTTERY_STATE);
				} catch (Exception e) {
					voteWinState = "";
					e.printStackTrace();
				}
			};

		}.start();
	}

	/**
	 * 连续点击后退按钮，退出程序
	 */
	private static long firstTime;

	@Override
	public void onBackPressed() {
		if (firstTime + 2000 > System.currentTimeMillis()) {
			// 退出应用程序，并杀死本应用程序的进程
			System.exit(0);
		} else {
			Toast.makeText(this, Server_Config.AGAIN_PRESS, Toast.LENGTH_SHORT)
					.show();
		}
		firstTime = System.currentTimeMillis();
	}

}
