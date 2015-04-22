package com.example.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.support.v4.app.*;

public class FragmentList extends ListFragment {

	String values[] = new String[] { "侏儒", "人类", "暗夜精灵", "矮人" };
	int images[] = new int[] { R.drawable.tu, R.drawable.tu1, R.drawable.tu3,
			R.drawable.tu4,

	};

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
		super.onListItemClick(l, v, position, id);
		FragmentDetails details= (FragmentDetails) getFragmentManager().findFragmentById(R.id.frament_details_demo);
		if(details !=null &&details.isInLayout()){
			switch(position){
			 case 0:  
				 details.setText("侏儒");
				 break;
			 case 1:  
				 details.setText("人类");
				 break;
			 case 2:  
				 details.setText("暗夜精灵");
				 break;
			 case 3:  
				 details.setText("矮人");
				 break;
			}
		}
	}
}
