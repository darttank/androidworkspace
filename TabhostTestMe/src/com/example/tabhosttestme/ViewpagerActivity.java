package com.example.tabhosttestme;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;

public class ViewpagerActivity extends Activity {
	
	List<View> listview;
	Context context = null;
	LocalActivityManager mannager = null ;
	TabHost tabhost = null ;
	private ViewPager pager = null ;
	 TabHost tabHost;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager);
		context = ViewpagerActivity.this;
		pager = (ViewPager) findViewById(R.id.viewpager);
		listview = new ArrayList<View>();
		
		mannager = new LocalActivityManager(this, true);
		mannager.dispatchCreate(savedInstanceState);
		
		Intent i1 = new Intent(context,T1Activity.class);
		listview.add(getView("A",i1));
		Intent i2 = new Intent(context,T1Activity.class);
		listview.add(getView("B",i2));
		Intent i3 = new Intent(context,T1Activity.class);
		listview.add(getView("C",i3));
		
		tabHost = (TabHost)findViewById(R.id.tabhost);
		tabHost.setup();
		tabHost.setup(mannager);
		
		

		//�����Ҫ���Զ���һ��tabhost�е�tab����ʽ
		RelativeLayout tabIndicator1 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tabwidget, null);  
		TextView tvTab1 = (TextView)tabIndicator1.findViewById(R.id.tv_title);
		tvTab1.setText("��һҳ");
		
		RelativeLayout tabIndicator2 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tabwidget,null);  
		TextView tvTab2 = (TextView)tabIndicator2.findViewById(R.id.tv_title);
		tvTab2.setText("�ڶ�ҳ");
		
		RelativeLayout tabIndicator3 = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tabwidget,null);  
		TextView tvTab3 = (TextView)tabIndicator3.findViewById(R.id.tv_title);
		tvTab3.setText("����ҳ");
		
		Intent intent = new Intent(context,EmptyActivity.class);
		tabHost.addTab(tabHost.newTabSpec("A").setIndicator(tabIndicator1).setContent(intent));
		tabHost.addTab(tabHost.newTabSpec("B").setIndicator(tabIndicator2).setContent(intent));
		tabHost.addTab(tabHost.newTabSpec("C").setIndicator(tabIndicator3).setContent(intent));
		pager.setAdapter(new MyPagerAdapter(listview));
		pager .setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				//��viewPager�����ı�ʱ��ͬʱ�ı�tabhost�����currentTab
				tabHost.setCurrentTab(position);
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
		
	 //���tabhost�е�tabʱ��Ҫ�л������viewPager
	 tabHost.setOnTabChangedListener(new OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
            	
            	if ("A".equals(tabId)) {
                    pager.setCurrentItem(0);
                } 
                if ("B".equals(tabId)) {
                	
                    pager.setCurrentItem(1);
                } 
                if ("C".equals(tabId)) {
                    pager.setCurrentItem(2);
                } 
            }
        });
	}

    private class   MyPagerAdapter extends PagerAdapter{

    	private List<View> list;

		public MyPagerAdapter(List<View> listview) {
			// TODO Auto-generated constructor stub
			this.list = listview;
		}

	

		@Override
        public void destroyItem(View view, int position, Object arg2) {
            ViewPager pViewPager = ((ViewPager) view);
            pViewPager.removeView(list.get(position));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object instantiateItem(View view, int position) {
            ViewPager pViewPager = ((ViewPager) view);
            pViewPager.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }

    }

	private View getView(String id,Intent intent) {
		// TODO Auto-generated method stub
		return mannager.startActivity(id, intent).getDecorView();
	}

	
}
