package com.lottery.rotary.mobile.android.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lottery.rotary.mobile.android.R;
import com.lottery.rotary.mobile.android.constant.Interface_Config;
import com.lottery.rotary.mobile.android.constant.Server_Config;
import com.lottery.rotary.mobile.android.json.LotteryJson;
import com.lottery.rotary.mobile.android.json.VoteJson;
import com.lottery.rotary.mobile.android.tools.DoubleClickUtils;

/**
 * 投票界面适配器
 * 
 * @author Alin
 * @time 2014-12-2上午10:10:58
 * @version 1.0.1
 * @remarks
 */
@SuppressLint("HandlerLeak")
public class VoteAdapter extends BaseAdapter {
	int[] icons = { R.drawable.vote_num1, R.drawable.vote_num2,
			R.drawable.vote_num3, R.drawable.vote_num4, R.drawable.vote_num5,
			R.drawable.vote_num6, R.drawable.vote_num7, R.drawable.vote_num8,
			R.drawable.vote_num9, R.drawable.vote_num10, R.drawable.vote_num11,
			R.drawable.vote_num12 };

	int[] programIcon = null;
	String[] programName = null;
	String[] programInfo = null;
	Context context;
	int count = 0;
	SharedPreferences sPreferences = null;
	String name = null;
	String workID = null;
	Message msg = null;
	Editor edit = null;

	private int max;

	public VoteAdapter(Context context) {
		this.context = context;
		/********************* 得到节目数和节目名开始 ***************************/
		SharedPreferences sp = context.getSharedPreferences("programID",
				Context.MODE_PRIVATE);
		max = sp.getInt("max", 0);
		programName = new String[max];
		programInfo = new String[max];
		programIcon = new int[max];
		for (int i = 0; i < max; i++) {
			// 得到节目名
			programName[i] = sp.getString((i + 1) + "", "");
		}
		for (int i = 0; i < max; i++) {
			System.out.println((i + 1) + "programName" + programName[i]);
		}

		/********************* 根据节目名得到表演者 ***************************/
		SharedPreferences sp2 = context.getSharedPreferences("program",
				Context.MODE_PRIVATE);
		for (int i = 0; i < max; i++) {
			programInfo[i] = sp2.getString(programName[i], "");
		}
		for (int i = 0; i < max; i++) {
			System.out.println(programName[i] + ":" + programInfo[i]);
		}
		// 根据节目名获得节目编号
		for (int i = 0; i < max; i++) {
			programIcon[i] = icons[i];
		}

		/********************* 得到已经被点赞的节目 ***************************/
		sPreferences = context.getSharedPreferences("item",
				Context.MODE_PRIVATE);
		// 得到员工号
		name = context.getSharedPreferences("itcast", Context.MODE_PRIVATE)
				.getString("name", "");
		// 得到员工id
		workID = context.getSharedPreferences("itcast", Context.MODE_PRIVATE)
				.getString("workID", "");
		// 得到已经点赞的数量
		if (sPreferences.getString("account", "").equals(name)) {
			for (int i = 0; i < max; i++) {
				if (sPreferences.getInt(i + "", 0) == 1) {
					count++;
				}
			}
		}
		System.out.println("count = " + count);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return max;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = null;
		view = View.inflate(context, R.layout.activity_votes_item, null);

		ImageView vote_num = (ImageView) view.findViewById(R.id.vote_num);
		TextView vote_name = (TextView) view.findViewById(R.id.vote_name);
		TextView vote_person = (TextView) view.findViewById(R.id.vote_person);
		final TextView support = (TextView) view.findViewById(R.id.support);

		vote_num.setImageResource(programIcon[position]);
		vote_name.setText(programName[position]);
		vote_person.setText(programInfo[position]);

		final Drawable drawableRed = context.getResources().getDrawable(
				R.drawable.vote_red);
		drawableRed.setBounds(0, 0, drawableRed.getMinimumWidth(),
				drawableRed.getMinimumHeight());

		final Drawable drawableBlue = context.getResources().getDrawable(
				R.drawable.vote_ok);
		drawableBlue.setBounds(0, 0, drawableBlue.getMinimumWidth(),
				drawableBlue.getMinimumHeight());

		// 首先匹配本地保存的投票XML文件中的员工姓名与登陆的员工姓名是否一致
		// 如果一致就根据本地保存的投票记录，设置点赞与否的显示
		if (sPreferences.getString("account", "").equals(name)) {
			for (int i = 0; i < max; i++) {
				if (sPreferences.getInt((position + 1) + "", 0) == 0) {
					support.setText("点赞");
				} else {
					support.setText("取消");
					support.setCompoundDrawables(drawableRed, null, null, null);
					support.setTextColor(context.getResources().getColor(
							R.color.red));
				}
			}
		}
		final Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					System.out.println("vote-->" + (position + 1) + "");
					support.setText("取消");
					support.setTextColor(context.getResources().getColor(
							R.color.red));
					support.setCompoundDrawables(drawableRed, null, null, null);
					break;
				case 2:
					support.setText("点赞");
					support.setTextColor(context.getResources().getColor(
							R.color.blue));
					support.setCompoundDrawables(drawableBlue, null, null, null);
					break;
				case 0:
					Toast.makeText(context, "操作失败了", Toast.LENGTH_SHORT).show();
					break;
				case 3:
					Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
					break;
				}
			};
		};

		/**
		 * 点击点赞按钮
		 */
		support.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				msg = handler.obtainMessage();
				if (DoubleClickUtils.isFastDoubleClick()) {
					System.out.println("无效的点击");
					return;
				} else {
					if (support.getText().toString().trim().equals("点赞")) {
						if (count >= 3) {
							Toast.makeText(context, "亲，每个人只能点3次赞哦",
									Toast.LENGTH_SHORT).show();
						} else {
							System.out.println("boolean ->" + (count < 3));
							new Thread() {
								public void run() {
									edit = sPreferences.edit();
									try {
										String vote = VoteJson.vote(
												Interface_Config.ADD_VOTE,
												(position + 1) + "", workID);
										System.out.println("voteTh-->"
												+ (position + 1) + "");
										String luckyFromJson = LotteryJson
												.getLuckyFromJson(
														vote,
														Server_Config.VOTE_STATE);
										if ("成功投票".equals(luckyFromJson)) {
											edit.putInt((position + 1) + "", 1);
											count++;
											msg.what = 1;
										} else {
											msg.what = 0;
										}
									} catch (Exception e) {
										msg.what = 3;
										e.printStackTrace();
									}
									edit.putString("account", name);
									edit.commit();
									handler.sendMessage(msg);
								};
							}.start();
						}
					} else {
						new Thread() {
							public void run() {
								msg = handler.obtainMessage();
								edit = sPreferences.edit();
								try {
									String vote = VoteJson.vote(
											Interface_Config.CANCEL_VOTE,
											(position + 1) + "", workID);
									System.out.println("cancelvoteTh-->"
											+ (position + 1) + "");
									String luckyFromJson = LotteryJson
											.getLuckyFromJson(
													vote,
													Server_Config.CANCEL_VOTE_STATE);
									if ("成功取消该投票".equals(luckyFromJson)) {
										edit.putInt((position + 1) + "", 0);
										System.out.println("cancelvote-->"
												+ (position + 1) + "");
										count--;
										msg.what = 2;
									} else {
										msg.what = 0;
									}
								} catch (Exception e) {
									msg.what = 3;
									e.printStackTrace();
								}
								handler.sendMessage(msg);
								edit.putString("account", name);
								edit.commit();

							};
						}.start();
					}
				}
			}
		});
		return view;
	}

}
