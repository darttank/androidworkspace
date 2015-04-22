package com.example.drawerdemo2;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends FragmentActivity {

	public static final String[] TITLES={"first","second"};
	public DrawerLayout mDrawer_layou;
	public RelativeLayout mMenu_layout_left;
	//public RelativeLayout mMenu_layout_right;
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDrawer_layou = (DrawerLayout) findViewById(R.id.drawer_layout);
		mMenu_layout_left = (RelativeLayout) findViewById(R.id.menu_layout_left);
		//mMenu_layout_right = (RelativeLayout) findViewById(R.id.menu_layout_right);
		
		//ListView menu_list_r = (ListView) mMenu_layout_right.findViewById(R.id.menu_listView_r);
		ListView menu_list_l = (ListView)mMenu_layout_left.findViewById(R.id.menu_list_l);
		
		//menu_list_r.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,TITLES));
		menu_list_l.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,TITLES));
	
		menu_list_l.setOnItemClickListener(new DrawerItemListenerLeft());
	//	menu_list_r.setOnItemClickListener(new DrawerItemListenerRight());
	}

     class DrawerItemListenerLeft implements OnItemClickListener{

		

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			 FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

	            Fragment fragment = null;
	            
	            switch (position) {
				case 0:
					fragment = new FirstFragment();
					break;
				case 1:
					fragment = new SecondFragment();
					break;

				default:
					break;
				}
	            ft.replace(R.id.fragment_layout, fragment);
	            ft.commit();
	           mDrawer_layou.closeDrawer(mMenu_layout_left);
	            
			  
			
		}
    	 
     }
     
   /*  class DrawerItemListenerRight implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			Fragment fragment =null;
			switch (position) {
			case 0:
				fragment = new FirstFragment();
				break;
			case 1:
				fragment = new SecondFragment();
				break;

			default:
				break;
			}
			ft.replace(R.id.fragment_layout, fragment);
			ft.commit();
			mDrawer_layou.closeDrawer(mMenu_layout_right);
		}
    	 
     }*/

}
