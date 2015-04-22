package com.example.tablehostdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import java.util.List;  
import java.util.List;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;


public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost = null;;
    private List<Fragment> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.fragments_tabs);


        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);


        mTabHost.addTab(mTabHost.newTabSpec("0").setIndicator("新闻"), NewsFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator("音乐"), MusicFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("2").setIndicator("人生"), LifeFragment.class, null);


    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mTabHost = null;
    }
}
