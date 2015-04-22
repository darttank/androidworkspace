package com.example.listviewdemo2;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ListViewAdapter extends BaseAdapter{
	List<String> list=null;
	Context  context;
	ListViewAdapter(Context context,List<String> list){
		this.context=context;
		this.list=list;
		
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
     //得到arg0位置的item
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}
     //id返回的是item的位置
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater=	(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		if(contentView==null){
			contentView= layoutInflater.inflate(R.layout.list_item, null);
			 ViewHolder viewHolder=new ViewHolder();
			 viewHolder.textview= (TextView) contentView.findViewById(R.id.textview);
			 viewHolder.textview.setText(list.get(position));
			/* ImageView iamge=(ImageView)view.findViewById(R.id.iamgeview);
			 viewHolder.iamgeview.seti*/
			 contentView.setTag(viewHolder);
			 
		}
	 ViewHolder viewHolder=(ViewHolder)contentView.getTag();
	 viewHolder.textview.setText(list.get(position));
		 
		  
		return contentView;
	}
	
	public  static class ViewHolder{
		public TextView textview;
		//public ImageView iamgeview;
		
		
	}
	
}