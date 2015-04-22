package com.example.listviewdemo3;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{
	
	Context context;
	List<Map<String,Object>> list;
	LayoutInflater layoutInflater;
	public ListViewAdapter(Context context,List<Map<String,Object>> list){
		
		this.context=context;
		this.list=list;
		
	 layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int postion, View contentView, ViewGroup arg2) {
		
		if(contentView == null){
			
			  contentView = layoutInflater.inflate(R.layout.listview_item, null);
			  
			  ViewHolder viewHolder =new ViewHolder();
		        
			  viewHolder.textview = (TextView) contentView.findViewById(R.id.textview);
		       
			  viewHolder.textview.setText((CharSequence) list.get(postion).get("name"));
			  
			  viewHolder.imageview = (ImageView) contentView.findViewById(R.id.imageview);
			  viewHolder.imageview.setImageResource((Integer) list.get(postion).get("pictrue"));
			  
			  contentView.setTag(viewHolder);
		        
		}else{
			
		    ViewHolder viewHolder=	(ViewHolder)contentView.getTag();
			viewHolder.textview.setText((CharSequence) list.get(postion).get("name"));
			viewHolder.imageview.setImageResource((Integer) list.get(postion).get("pictrue"));
		}
		
      
			 
		return contentView;
	}
	
	public static class  ViewHolder {
		public  TextView textview;
		public  ImageView imageview;
		
	}

}
