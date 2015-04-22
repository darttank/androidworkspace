package com.lottery.rotary.mobile.android.activity;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.lottery.rotary.mobile.android.R;
import com.lottery.rotary.mobile.android.constant.Interface_Config;
import com.lottery.rotary.mobile.android.constant.Server_Config;
import com.lottery.rotary.mobile.android.json.LotteryJson;
import com.lottery.rotary.mobile.android.json.ProgramJson;
import com.lottery.rotary.mobile.android.json.VoteJson;
import com.lottery.rotary.mobile.android.tools.PreferencesTools;
import com.lottery.rotary.mobile.android.tools.ShakeListener;
import com.lottery.rotary.mobile.android.tools.ShakeListener.OnShakeListener;
import com.slidingmenu.lib.SlidingMenu;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 抽奖界面
 * 
 * @author Alin
 * @time 2014-11-11上午9:38:21
 * @version 1.0.1 备注：
 */
public class LotteryActivity extends BaseActivity {
	private ImageView imView, imcount, imgnot;
	private int icon[] = { R.drawable.image_left, R.drawable.image_middle,
			R.drawable.image_right };
	private int icoSotp[] = { R.drawable.lottery_result };
	private int index = 0;
	private int randomC = 0;
	private boolean flag = true;
	
	
	protected static final int SUCCESS = 1;
	protected static final int ERROR = 0;
	protected static final int ISTOTTERYNOTXML = 2;
	protected static final int ISTOTTERY = 3;
	private TextView yaoyao;
	SharedPreferences sp = null;
	private String workID = null;
	private String name = null;
	// private String luckyStates = null;
	private String voteWinState = null;
	String keyWord = "恭喜";
	private String lucky = null; // json解析的中奖信息
	private List<String> nameList;
	private boolean isExsit = false; // 本地xml是否中奖
	private boolean find = false; // 接口是否中奖
	private boolean isLottery = false; // 当接口返回的值中和本地xml返回的值都有中奖的信息，就表示已经中奖

	private RelativeLayout shakeImgUp, shakeImgDown;
	private LinearLayout personal_logout, personal_person_centre_quit;
	Vibrator vibrator = null; // 震动
	ShakeListener shakeListener;
	private ImageView toVote, personal_center;
	private TextView person_centre_name, person_centre_workID,
			person_centre_luckstate, vote_state;

	private SlidingMenu menu; // 侧滑菜单
	private SharedPreferences lotterySp;

	@SuppressLint("HandlerLeak")
	public Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case SUCCESS:
				String lucky = (String) msg.obj;
				AlertDialog dialog = new AlertDialog.Builder(
						LotteryActivity.this)
						.setTitle("中奖提示")
						.setMessage(lucky)
						.setPositiveButton(
								"继续",
								new android.content.DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										vibrator.cancel();
										shakeListener.start();
										//yaoyao.setVisibility(View.VISIBLE);
										//toVote.setClickable(true);
									}
								}).create();
				dialog.show();
				dialog.setCanceledOnTouchOutside(false);
				break;
			case ISTOTTERYNOTXML:
				String lucky2 = (String) msg.obj;
				AlertDialog dialog1 = new AlertDialog.Builder(
						LotteryActivity.this)
						.setTitle("中奖提示")
						.setMessage(lucky2)
						.setPositiveButton(
								"继续",
								new android.content.DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										vibrator.cancel();
										shakeListener.start();
										yaoyao.setVisibility(View.VISIBLE);
										toVote.setClickable(true);
									}
								}).create();
				dialog1.show();
				dialog1.setCanceledOnTouchOutside(false);
				break;
			case ISTOTTERY:
				AlertDialog dialog2 = new AlertDialog.Builder(
						LotteryActivity.this)
						.setTitle("中奖提示")
						.setMessage("继续摇吧，奖品就在眼前")
						.setPositiveButton(
								"继续",
								new android.content.DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										vibrator.cancel();
										shakeListener.start();
										yaoyao.setVisibility(View.VISIBLE);
										toVote.setClickable(true);
									}
								}).create();
				dialog2.show();
				dialog2.setCanceledOnTouchOutside(false);
				break;
			case ERROR:
				Toast.makeText(getApplicationContext(), "您的网络出现问题",
						Toast.LENGTH_LONG).show();
				vibrator.cancel();
				shakeListener.start();
				yaoyao.setVisibility(View.VISIBLE);
				toVote.setClickable(true);
				break;
				
			case 4:
				if (index < icon.length - 1) {
					index++;
				} else {
					index = 0;
				}
				imView.setBackgroundResource(icon[index]);
				handler.sendEmptyMessageDelayed(4, 170); // 控制 鸡蛋摇奖的速度
				   System.out.println("执行了此段代码");
				//break;
			case 5:
				imView.setBackgroundResource(icoSotp[0]);
				// imcount.setBackgroundResource(icoSotp[1]);
				handler.removeMessages(4);
				AlertDialog dialog3 = new AlertDialog.Builder(
						LotteryActivity.this)
						.setTitle("中奖提示")
						.setMessage("你好")
						.setPositiveButton(
								"继续",
								new android.content.DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										vibrator.cancel();
										shakeListener.start();
										flag = true;
										// yaoyao.setVisibility(View.VISIBLE);
									}
								}).create();
				dialog3.show();
				dialog3.setCanceledOnTouchOutside(false);
				shakeListener.start();
				break;
				
			}
		};
	};

	@Override
	public void setContentView() {
		setContentView(R.layout.activity_lottery);
		sp = getSharedPreferences("itcast", MODE_PRIVATE);
		name = sp.getString("name", "");
		workID = sp.getString("workID", "");
		
		imView = (ImageView) this.findViewById(R.id.imgmiddle);
		imcount = (ImageView) this.findViewById(R.id.imgtit);
		imgnot = (ImageView) this.findViewById(R.id.imgnoth);
		
		lotterySp = getSharedPreferences("lottery", MODE_PRIVATE);
		String xmlState = lotterySp.getString("lottery_state", "");
		isExsit = Pattern.compile(keyWord).matcher(xmlState).find();
		System.out.println("isExsit" + isExsit);
		
		
		/**
		 * 加载界面的同时，获得中奖状态
		 */
		new Thread() {

			public void run() {
				try {
					String states = LotteryJson.getLuckyStates(name, workID);
					System.out.println("workid=" + workID + ",name=" + name);
					lucky = LotteryJson.getLuckyFromJson(states,
							Server_Config.LUCY_STATE);
					System.out.println("登陆的时候就获取状态lucky-->" + lucky);

					find = Pattern.compile(keyWord).matcher(lucky).find();

					System.out.println("find-->" + find);
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
		getPrograme();
		getVoteState();
		getVoteWinState();

		// System.out.println("find2-->" + find);
		// System.out.println("findlucky-->" + lucky);
		// find = Pattern.compile(keyWord).matcher(lucky).find();

		updateLottery(workID);

	}

	@Override
	public void initViews() {
		/**************** 初始化侧滑菜单 ******************/
		menu = new SlidingMenu(getApplicationContext());
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.attachToActivity(LotteryActivity.this, SlidingMenu.SLIDING_CONTENT);
		menu.setBehindOffset(170);
		menu.setFadeDegree(0.35f);
		menu.setMenu(R.layout.personal_center);

	/*	shakeImgDown = (RelativeLayout) findViewById(R.id.shakeImgDown);
		shakeImgUp = (RelativeLayout) findViewById(R.id.shakeImgUp);
		yaoyao = (TextView) findViewById(R.id.yaoyao);*/
		toVote = (ImageView) findViewById(R.id.toVote);
		personal_center = (ImageView) findViewById(R.id.person_center);
		person_centre_name = (TextView) findViewById(R.id.person_centre_name);
		person_centre_workID = (TextView) findViewById(R.id.person_centre_workID);
		person_centre_luckstate = (TextView) findViewById(R.id.person_centre_luckstate);
		vote_state = (TextView) findViewById(R.id.vote_state);
		personal_logout = (LinearLayout) findViewById(R.id.personal_logout);
		personal_person_centre_quit = (LinearLayout) findViewById(R.id.personal_person_centre_quit);
		person_centre_name.setText(name);
		person_centre_workID.setText(workID);

		System.out.println("init--> find and isExsit" + find + isExsit);
		if (lotterySp.getString("workId", "").equals(workID)) {
			person_centre_luckstate.setText(lotterySp.getString(
					"lottery_state", ""));
		} else {
			person_centre_luckstate.setText("未中奖");
		}
		if (voteWinState == null) {
			vote_state.setText("未中奖");
		}
		//yaoyao.setOnClickListener(this);
		//toVote.setOnClickListener(this);
		personal_center.setOnClickListener(this);
		personal_logout.setOnClickListener(this);
		personal_person_centre_quit.setOnClickListener(this);

		// 得到系统服务Vibrator服务
		vibrator = (Vibrator) getApplicationContext().getSystemService(
				VIBRATOR_SERVICE);
		shakeListener = new ShakeListener(getApplicationContext());

		shakeListener.setOnShakeListener(new OnShakeListener() {

			@Override
			public void onShake() {
				
				lottery();
				
			

				
			}

		});
	}

	private void lottery() {
		/*yaoyao.setVisibility(View.GONE);
		toVote.setClickable(false);*/
		
		
			imgnot.setVisibility(View.GONE);

			startVibrator();

			shakeListener.stop();

			handler.sendEmptyMessageDelayed(4, 100);

			handler.sendEmptyMessageDelayed(5, 4000);

			flag = false;

		

		startAnim();// 开始摇
		shakeListener.stop();

		startVibrato(); // 开始震动
		/*
		 * // 延迟10秒才能监测下一次开始 new Handler().postDelayed(new Runnable() {
		 * 
		 * @Override public void run() { if (isOk) { vibrator.cancel();
		 * shakeListener.start(); }
		 * 
		 * } }, 10000);
		 */
	}
	
	public void startVibrator() {
		vibrator.vibrate(new long[] { 500, 300, 500, 300 }, -1);
	}

	/**
	 * 开始震动
	 */
	protected void startVibrato() {
		// 声音
		MediaPlayer player;
		player = MediaPlayer.create(this, R.raw.awe);
		player.setLooping(false);
		player.start();

		// 定义震动
		vibrator.vibrate(new long[] { 500, 200, 500, 200 }, -1); // 第一个｛｝里面是节奏数组，
	}

	/**
	 * 定义摇一摇动画
	 */
	protected void startAnim() {
		/*AnimationSet animup = new AnimationSet(true);
		TranslateAnimation mytranslateanimup0 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
				-0.5f);
		mytranslateanimup0.setDuration(1000);
		TranslateAnimation mytranslateanimup1 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
				+0.5f);
		mytranslateanimup1.setDuration(1000);
		mytranslateanimup1.setStartOffset(1000);
		animup.addAnimation(mytranslateanimup0);
		animup.addAnimation(mytranslateanimup1);
		shakeImgUp.startAnimation(animup);

		AnimationSet animdn = new AnimationSet(true);
		TranslateAnimation mytranslateanimdn0 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
				+0.5f);
		mytranslateanimdn0.setDuration(1000);
		TranslateAnimation mytranslateanimdn1 = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
				-0.5f);
		mytranslateanimdn1.setDuration(1000);
		mytranslateanimdn1.setStartOffset(1000);
		animdn.addAnimation(mytranslateanimdn0);
		animdn.addAnimation(mytranslateanimdn1);
		shakeImgDown.startAnimation(animdn);*/

		shakeListener.stop();
		startVibrato();

		new Thread(new Runnable() {
			final Message msg = new Message();

			@Override
			public void run() {
				String xmlState = lotterySp.getString("lottery_state", "");
				isExsit = Pattern.compile(keyWord).matcher(xmlState).find();
				if (find && isExsit) {
					isLottery = true;
				}

				if (isLottery) {
					System.out.println("不再发送请求");
					msg.what = ISTOTTERY;
				} else {
					if (find && !isExsit) {
						System.out.println("已经中奖，但是本地xml没有写进去");
						lotterySp.edit().putString("lottery_state", lucky)
								.putString("workId", workID).commit();
						msg.obj = lucky;
						msg.what = ISTOTTERYNOTXML;
						System.out.println("luck-->" + lucky);

					} else {
						try {
							System.out.println("发送请求");
							// 向服务器发送请求
							PreferencesTools tools = new PreferencesTools(
									LotteryActivity.this);
							Map<String, String> preferences = tools
									.getPreferences();
							String states = LotteryJson.getLuckyStates(
									preferences.get("name"),
									preferences.get("workID"));
							String luckys = LotteryJson.getLuckyFromJson(
									states, Server_Config.LUCY_STATE);
							System.out.println("luckys-->" + luckys);
							find = Pattern.compile(keyWord).matcher(luckys)
									.find();
							lotterySp.edit().putString("lottery_state", luckys)
									.commit();
							msg.obj = luckys;
							msg.what = SUCCESS;
						} catch (Exception e) {
							msg.what = ERROR;
							e.printStackTrace();
						}
					}
				}
				SystemClock.sleep(3000);
				handler.sendMessage(msg);
			}
		}).start();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.toVote:
			Intent intent2 = new Intent(getApplicationContext(),
					SVoteActivity.class);
			startActivity(intent2);
			finish();
			break;

		// 点击，摇奖
		/*case R.id.yaoyao:
			// yaoyao.setVisibility(View.GONE);
			lottery();
			break;*/
		// 打开个人中心
		case R.id.person_center:
			menu.toggle();
			break;
		// 个人中心退出
		case R.id.personal_person_centre_quit:
			System.exit(0);
			break;
		// 个人中心的注销
		case R.id.personal_logout:
			Intent intent = new Intent(getApplicationContext(),
					LoginActivity.class);
			startActivity(intent);
			finish();
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (shakeListener != null) {
			shakeListener.stop();
		}
	}

	/**
	 * 得到投票状态
	 */
	private void getVoteState() {
		new Thread() {
			public void run() {
				try {
					String voteState = VoteJson.voteState(
							Interface_Config.VOTE_STATE, workID);
					// System.out.println(voteState);
					List<Integer> list = VoteJson.getVoteFromJson(voteState,
							"GameState");

					Editor edit = getSharedPreferences("item",
							Context.MODE_PRIVATE).edit();
					for (int i = 0; i < nameList.size(); i++) {
						edit.putInt((i + 1) + "", 0);
					}
					for (int i = 0; i < list.size(); i++) {
						edit.putInt(list.get(i) + "", 1);
					}
					edit.putString("account", name);
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
	 * 获得节目列表
	 */
	private void getPrograme() {
		new Thread() {

			private List<String> infromList;
			private List<Integer> iDList;

			public void run() {
				try {
					String result = ProgramJson.getProgram();

					Map<List<String>, List<String>> map = ProgramJson
							.getProInfoFromJson(result);
					Map<List<Integer>, List<String>> map2 = ProgramJson
							.getProIDFromJson(result);
					if (map.isEmpty() || map2.isEmpty()) {
						return;
					}

					Iterator<Entry<List<String>, List<String>>> iterator = map
							.entrySet().iterator();
					while (iterator.hasNext()) {
						Entry<List<String>, List<String>> entry = iterator
								.next();
						nameList = entry.getKey();
						infromList = entry.getValue();
					}
					Editor editor = getSharedPreferences("program",
							Context.MODE_PRIVATE).edit();
					for (int i = 0; i < nameList.size(); i++) {
						editor.putString(nameList.get(i), infromList.get(i));
					}

					editor.commit();

					Iterator<Entry<List<Integer>, List<String>>> iterator1 = map2
							.entrySet().iterator();
					while (iterator1.hasNext()) {
						Entry<List<Integer>, List<String>> entry = iterator1
								.next();
						iDList = entry.getKey();
						nameList = entry.getValue();
					}
					Editor editor1 = getSharedPreferences("programID",
							Context.MODE_PRIVATE).edit();
					for (int i = 0; i < nameList.size(); i++) {
						editor1.putString(iDList.get(i) + "", nameList.get(i));
					}
					editor1.putInt("max", nameList.size());
					editor1.commit();

				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();

	}

	private void updateLottery(final String workID) {
		new Thread() {
			public void run() {
				try {
					String states = LotteryJson.getLuckyStates(name, workID);
					lucky = LotteryJson.getLuckyFromJson(states,
							Server_Config.LUCY_STATE);
					System.out.println("登陆的时候就获取状态lucky-->" + lucky);

					find = Pattern.compile(keyWord).matcher(lucky).find();
					if (find) {
						System.out.println("已经中奖了，就不改了");
					} else {
						System.out.println("没中奖，改一改吧");
						// LoginJson.updateStateToLottery(workID);
					}
				} catch (Exception e) {
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
