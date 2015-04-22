package com.example.listviewdemo4;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{
	
	List<Map<String, Object>> list;
	Context context;
	LayoutInflater layoutInflater;
	ListViewAdapter(List<Map<String, Object>> list,Context context){
		this.list=list;
		this.context=context;
		
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
	public View getView(int position, View contentView, ViewGroup arg2) {
		 TextView textview;
		 ImageView imageView;
	
		//�ж�contentView�Ƿ�Ϊ�գ�Ϊ�վʹ����µ�view
		if(contentView == null){
		
			Log.i("tianfan","contentViewΪ��ʱ��λ��: "+position);
			contentView =layoutInflater.inflate(R.layout.list_item, null);
			
			textview=(TextView)contentView.findViewById(R.id.textview);
			 
			 imageView=(ImageView)contentView.findViewById(R.id.imageview);
			
			
			contentView.setTag(new ViewHolder(textview,imageView));
		}else{
			 
		
			//contentView��Ϊ�գ������Ѿ����ڵ�contentView���������holder�ó���
			ViewHolder viewHolder=(ViewHolder) contentView.getTag();
			//��Ҫ�ظ�Ѱ��item�пؼ�
			imageView=viewHolder.imageView;
			textview=viewHolder.textview;
			
		}
		textview.setText((CharSequence) list.get(position).get("name"));
		imageView.setImageResource((Integer) list.get(position).get("pictrue"));
		
		return contentView;
	}
	
	private static class ViewHolder{
		public ViewHolder(TextView textview, ImageView imageView) {
			// TODO Auto-generated constructor stub
			this.textview=textview;
			this.imageView=imageView;
		}
		public TextView textview;
		public ImageView imageView;
	}

}
