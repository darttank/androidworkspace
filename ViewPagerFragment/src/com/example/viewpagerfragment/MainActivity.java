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
     * ����ѡ��µ��»��ߵ�imageview
     */
    private ImageView cursor = null;
    /**
     * ѡ��»��߳���
     */
    private static int lineWidth = 0;
    
    /**
     * ƫ����
     *         ���ֻ���Ļ���/3-ѡ����ȣ�/2
     */
    private static int offset = 0;
    
    /**
     * ѡ�����
     */
    private static final int TAB_COUNT = 3;
    /**
     * ��ǰ��ʾ��ѡ�λ��
     */
    private int current_index = 0;
    
    /**
     * ѡ�����
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
            public Fragment getItem(int index)//ֱ�Ӵ���fragment���󲢷���
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
            int one = offset * 2 + lineWidth;// ҳ��1 -> ҳ��2 ƫ����
            @Override
            public void onPageSelected(int index)//���ñ������ɫ�Լ��»��ߵ��ƶ�Ч��
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
        //��ȡͼƬ���
        lineWidth = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //��ȡ��Ļ���
        int screenWidth = dm.widthPixels;
        Matrix matrix = new Matrix();
        offset = (int) ((screenWidth/(float)TAB_COUNT - lineWidth)/2);
        matrix.postTranslate(offset, 0);
        //���ó�ʼλ��
        cursor.setImageMatrix(matrix);
    }
}


