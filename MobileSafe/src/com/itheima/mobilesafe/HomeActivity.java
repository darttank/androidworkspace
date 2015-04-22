package com.itheima.mobilesafe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends Activity {
	GVAdapter GVadapter;
    GridView gridivew_function;
    ImageView item_image;
    TextView  item_text;
    private static String [] names = 
    	{"手机防盗","通讯卫士","软件管理",
          "进程管理","流量统计","手机杀毒",
          "缓存清理","高级工具","设置中心"
    };
    private static int [] image_id = {
    	R.drawable.safe,R.drawable.callmsgsafe,R.drawable.app,
		R.drawable.taskmanager,R.drawable.netmanager,R.drawable.trojan,
		R.drawable.sysoptimize,R.drawable.atools,R.drawable.settings
    	
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		gridivew_function = (GridView) findViewById(R.id.gridview_function);
		GVadapter = new GVAdapter();
		gridivew_function.setAdapter(GVadapter);
		
		gridivew_function.setOnItemClickListener(new OnItemClickListener() {
			Intent intent;
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				switch (position) {
				case 8:
					 intent = new Intent(HomeActivity.this,SettingActivity.class);
					break;
				case 0:
					 intent = new Intent(HomeActivity.this,SettingActivity.class);
				
					break;
					
					

				default:
					break;
				}
				startActivity(intent);
			}
			
		});
	}
	

private class GVAdapter extends BaseAdapter{

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return names[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertview, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View view = View.inflate(HomeActivity.this, R.layout.grid_item, null);
		item_image = (ImageView) view.findViewById(R.id.gv_item_image);
		item_text = (TextView) view.findViewById(R.id.gv_item_text);
		item_image.setBackgroundResource(image_id[position]);
		item_text.setText(names[position]);
		return view;
	}
	



}

}
