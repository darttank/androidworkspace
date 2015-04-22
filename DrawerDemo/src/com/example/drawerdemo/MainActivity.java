package com.example.drawerdemo;

import FirstFragment.FirstFragment;
import FirstFragment.SecondFragment;
import android.os.Bundle;

import android.app.Activity;
import android.support.v4.app.*;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;

import android.support.v4.app.FragmentTransaction;

import android.support.v4.widget.DrawerLayout;

import android.view.Gravity;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.ListView;

import android.widget.RelativeLayout;

import android.widget.AdapterView.OnItemClickListener;

import android.widget.TextView;

public class MainActivity extends FragmentActivity

{

                

    public static final String[] TITLES = { "First", "Second" };

    private DrawerLayout mDrawer_layout;//DrawerLayout����

    private RelativeLayout mMenu_layout_left;//��߳���

    private RelativeLayout mMenu_layout_right;//�ұ߳���

                      

    @Override

    protected void onCreate(Bundle savedInstanceState)

    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

                          

        mDrawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mMenu_layout_left = (RelativeLayout) findViewById(R.id.menu_layout_left);

        mMenu_layout_right = (RelativeLayout) findViewById(R.id.menu_layout_right);

        ListView menu_listview_l = (ListView) mMenu_layout_left.findViewById(R.id.menu_listView_l);

        ListView menu_listview_r = (ListView) mMenu_layout_right.findViewById(R.id.menu_listView_r);

                          

        menu_listview_l.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, TITLES));

        menu_listview_r.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, TITLES));

                          

        //�����˵�

        menu_listview_l.setOnItemClickListener(new DrawerItemClickListenerLeft());

        menu_listview_r.setOnItemClickListener(new DrawerItemClickListenerRight());

    }

    /**

     * ����б����¼�      

     * @author busy_boy

     *

     */

    public class DrawerItemClickListenerLeft implements OnItemClickListener

    {

        @Override

        public void onItemClick(AdapterView<?> parent, View view, int position, long id)

        {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            Fragment fragment = null;

                              

            //����item����к��ж������ĸ�Fragment

            switch (position)

            {

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

            mDrawer_layout.closeDrawer(mMenu_layout_left);//�ر�mMenu_layout

        }

                          

    }

    /**

     * �Ҳ��б����¼�      

     * @author busy_boy

     *

     */

    private class DrawerItemClickListenerRight implements OnItemClickListener {

     @Override

        public void onItemClick(AdapterView<?> parent, View view, int position, long id)

        {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            Fragment fragment = null;

                              

            //����item����к��ж������ĸ�Fragment

            switch (position)

            {

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

            mDrawer_layout.closeDrawer(mMenu_layout_right);//�ر�mMenu_layout

        }

   }

}

