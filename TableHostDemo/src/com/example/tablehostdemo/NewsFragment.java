package com.example.tablehostdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NewsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        android.util.Log.d("mark", "onCreate()--------->news Fragment");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.news, null);
        android.util.Log.d("mark", "onCreateView()--------->news Fragment");
        return view;
    }


    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        android.util.Log.d("mark", "onPause()--------->news Fragment");
    }
 
}
