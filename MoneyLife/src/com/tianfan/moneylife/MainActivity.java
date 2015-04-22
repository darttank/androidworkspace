package com.tianfan.moneylife;



import java.util.ArrayList;
import java.util.List;

import com.slidingmenu.lib.SlidingMenu;
import com.tianfan.bean.MenuItem;
import com.tianfan.control.MenuItemAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends FragmentActivity {

	private ViewPager vp_content;
	private TextView tv_find;
	private TextView tv_my_file;
	private SlidingMenu slidingMenu;
	private ImageButton ibtn_trigger;
	private ImageView iv_menu_icon;
	private GridView gv_menu;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gv_menu = (GridView)findViewById(R.id.gv_menu);
		//初始化上面导航栏
		findView();
		//初始化侧滑
     	init_sildingMenu();
     	//初始化gridview
     	 init_gridView();
     	// gridview的点击事件
     	onclick_gridview(gv_menu);
	  // ToAnother();
     	
	}
	
	/*public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_find, container, false);
		findView2(view);
		init2();
		return view;
	}*/

	private void onclick_gridview(View v) {
		
		//gridview 点击事件
		gv_menu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch(arg2){
				case 0:
					intent=new Intent(MainActivity.this,AddEvent.class);
					startActivity(intent);
						break;
				case 1:
					intent=new Intent(MainActivity.this,AddIncome.class);
					startActivity(intent);
					break;
				case 2:
					intent=new Intent(MainActivity.this,QueryByDateActivity.class);
					startActivity(intent);
					break;
				case 3:
					intent=new Intent(MainActivity.this,BudgetActivity.class);
					startActivity(intent);
					break;
				case 4:
					 intent=new Intent(MainActivity.this,AnalysisActivity.class);
					 startActivity(intent);
					break;
				case 5:
					intent=new Intent(MainActivity.this,SettingActivity.class);
					startActivity(intent);
					break;
				}
			}
		});
	}

	private void init_gridView() {
		List<MenuItem> menus = new ArrayList<MenuItem>();
		menus.add(new MenuItem(R.drawable.menu_leidian, "添加支出", " "));
		menus.add(new MenuItem(R.drawable.menu_downloaded, "添加收入", ""));
		menus.add(new MenuItem(R.drawable.menu_photo, "账单查询", ""));
		menus.add(new MenuItem(R.drawable.menu_video, "预算设置", ""));
		menus.add(new MenuItem(R.drawable.menu_file, "数据分析", ""));
		menus.add(new MenuItem(R.drawable.menu_music, "软件设置", ""));
		// 计算margin
		int margin = (int) (getResources().getDisplayMetrics().density * 14 * 13 / 9);
		MenuItemAdapter adapter = new MenuItemAdapter(getApplicationContext(), menus, margin);
		gv_menu.setAdapter(adapter);
	}
	private void ToAnother() {
		// TODO Auto-generated method stub
		
		iv_menu_icon = (ImageView)findViewById(R.id.iv_menu_icon);
	    intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		
	}

	private void findView() {
	//	vp_content = (ViewPager) findViewById(R.id.vp_content);
		//tv_find = (TextView) findViewById(R.id.tv_find);
		//tv_my_file = (TextView) findViewById(R.id.tv_my_file);
		ibtn_trigger = (ImageButton) findViewById(R.id.ibtn_right_menu);
	}

	private void init_sildingMenu() {
		/*vp_content.setAdapter(new ContentPagerAdapter(getSupportFragmentManager()));
		vp_content.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				setCurrentPage(position);
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// ignore
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// ignore
			}
		});*/

		ibtn_trigger.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				slidingMenu.toggle();
			}
		});

		slidingMenu = new SlidingMenu(this);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE); // 滑动方式
		slidingMenu.setShadowDrawable(R.drawable.shadow_right); // 阴影
		slidingMenu.setShadowWidth(30); // 阴影宽度
		slidingMenu.setBehindOffset(80); // 前面的视图剩下多少
		slidingMenu.setMode(SlidingMenu.RIGHT); // 左滑出不是右滑出
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.menu_frame); // 设置menu容器
		FragmentManager fm = getSupportFragmentManager();
		fm.beginTransaction().replace(R.id.menu_frame, new MenuFragment()).commit();
	}

	// 按下返回键时
	@Override
	public void onBackPressed() {
		if (slidingMenu != null && slidingMenu.isMenuShowing()) {
			slidingMenu.showContent();
		} else {
			super.onBackPressed();
		}
	}

	// 按下菜单键时
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			slidingMenu.toggle();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/*private void setCurrentPage(int current) {
		if (current == 0) {
			tv_find.setBackgroundResource(R.drawable.title_menu_current);
			tv_find.setTextColor(getResources().getColor(R.color.blue));
			tv_my_file.setBackgroundResource(R.drawable.title_menu_bg);
			tv_my_file.setTextColor(getResources().getColor(R.color.grey));
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		} else {
			
			tv_my_file.setBackgroundResource(R.drawable.title_menu_current);
			tv_my_file.setTextColor(getResources().getColor(R.color.blue));
			tv_find.setBackgroundResource(R.drawable.title_menu_bg);
			tv_find.setTextColor(getResources().getColor(R.color.grey));
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}
	}*/
}