package com.example.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentDetails extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_details, container, false);
	}
	public void setText(String details){
		TextView textview=(TextView) getView().findViewById(R.id.textview_details);
		textview.setText(details);
	}
}
