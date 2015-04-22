package com.example.viewpagerfragment;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;


import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity
{
    private ViewPager vPager = null;
    /**
     * 代表选项卡下的下划线的imageview
     */
    private ImageView cursor = null;
    /**
     * 选项卡下划线长度
     */
    private static int lineWidth = 0;
    
    /**
     * 偏移量
     *         （手机屏幕宽度/3-选项卡长度）/2
     */
    private static int offset = 0;
    
    /**
     * 选项卡总数
     */
    private static final int TAB_COUNT = 3;
    /**
     * 当前显示的选项卡位置
     */
    private int current_index = 0;
    
    /**
     * 选项卡标题
     */
    private TextView text1,text2,text3;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = (ViewPager) findViewById(R.id.vPager);
        
        initImageView();
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        final TextView[] titles = {text1,text2,text3};
        vPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public int getCount()
            {
                return TAB_COUNT;
            }
            
            @Override
            public Fragment getItem(int index)//直接创建fragment对象并返回
            {
                switch (index)
                {
                case 0:
                    return new Tab1();
                case 1:
                    return new Tab2();
                case 2:
                    return new Tab3();
                }
                return null;
            }
        });
       vPager.setOnPageChangeListener(new OnPageChangeListener()
        {
            int one = offset * 2 + lineWidth;// 页卡1 -> 页卡2 偏移量
            @Override
            public void onPageSelected(int index)//设置标题的颜色以及下划线的移动效果
            {
                Animation animation = new TranslateAnimation(one*current_index,one*index, 0,0);
                animation.setFillAfter(true);
                animation.setDuration(300);
                cursor.startAnimation(animation);
                titles[current_index].setTextColor(Color.BLACK);
                titles[index].setTextColor(Color.RED);
                current_index = index;
            }
            
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2)
            {
            }
            
            @Override
            public void onPageScrollStateChanged(int index)
            {
            }
        });
    }
    private void initImageView()
    {
        cursor = (ImageView) findViewById(R.id.cursor);
        //获取图片宽度
        lineWidth = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //获取屏幕宽度
        int screenWidth = dm.widthPixels;
        Matrix matrix = new Matrix();
        offset = (int) ((screenWidth/(float)TAB_COUNT - lineWidth)/2);
        matrix.postTranslate(offset, 0);
        //设置初始位置
        cursor.setImageMatrix(matrix);
    }
}


