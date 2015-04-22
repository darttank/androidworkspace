package com.example.fragmentdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.support.v4.app.*;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
public class FragmentList extends ListFragment {

	String values[] = new String[] { "٪��", "����", "��ҹ����", "����" };
	int images[] = new int[] { R.drawable.tu, R.drawable.tu1, R.drawable.tu3,
			R.drawable.tu4};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 return inflater.inflate(R.layout.fragment_list, container, false);  
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < values.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("values", values[i]);
			map.put("images", images[i]);
			list.add(map);
		}
		SimpleAdapter adapter=new SimpleAdapter(this.getActivity(), 
				list, R.layout.fragment_list_item, new String[]{"values","images"}, 
				new int[]{R.id.textview,R.id.imageview});
		setListAdapter(adapter);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Log.i("Tag", "������list"+position);
		//super.onListItemClick(l, v, position, id);
	//	FragmentDetails details= (FragmentDetails) getFragmentManager().findFragmentById(R.id.frament_details_demo);
		//FragmentDetails details=(FragmentDetails) getFragmentManager().findFragmentById(R.id.frament_details_demo);
//		if(details !=null &&details.isInLayout()){
			FragmentDetails details=(FragmentDetails) getFragmentManager().findFragmentById(R.id.frament_details_demo);
			 
			 
			switch(position){
			 case 0:  
				 details.setText("٪��");
				 break;
			 case 1:  
				 details.setText("����");
				 break;
			 case 2:  
				 details.setText("��ҹ����");
				 break;
			 case 3:  
				 details.setText("����");
				 break;
			}
		}
	}
//}
