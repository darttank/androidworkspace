package com.example.adapter;

import java.io.File;
import java.util.List;

import com.example.domain.Contact;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {
	
	int listview_item=0;
	File cache=null;
	List<Contact> data=null;
	LayoutInflater layoutInflater;
	
   
	public ContactAdapter(Context context, List<Contact> data ,int listview_item,
			File cache) {
	
		this.listview_item=listview_item;
		this.cache=cache;
		this.data=data;
		// TODO Auto-generated constructor stub
		layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View contentView, ViewGroup arg2) {
		
		contentView = layoutInflater.inflate(com.example.dataayscloaddemo.R.layout.listview_item, null);
		
		
		return contentView;
	}
		// TODO Auto-generated method stub
		/* TextView textview;
		 ImageView image;
		 
		if(contentView==null){
			contentView = layoutInflater.inflate(listview_item, null);
			   textview= (TextView) contentView.findViewById(com.example.dataayscloaddemo.R.id.textview);
			   image= (ImageView) contentView.findViewById(com.example.dataayscloaddemo.R.id.imageview);
			   contentView.setTag(new Wapper(image,textview));
		}
		else{
			Wapper wapper=(Wapper)contentView.getTag();
			image=wapper.image;
			textview = wapper.textview;
			
			
		}
	  
	    
	    
		
	    return contentView;
	}
	private class Wapper{
		public ImageView image;
		public TextView textview;
		public Wapper(ImageView image,TextView textview){
			this.image=image;
			this.textview=textview;
			
		}
		
	}*/

}
