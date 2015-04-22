package com.example.listviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends Activity {
	ListView listView;	//声明一个ListView对象
	private List<info> mlistInfo = new ArrayList<info>();  //声明一个list，动态存储要显示的信息
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);       
        
        listView=(ListView)this.findViewById(R.id.listView);	//将listView与布局对象关联
        
        setInfo();	//给信息赋值函数，用来测试
        
        listView.setAdapter(new ListViewAdapter(mlistInfo));
        
        //处理Item的点击事件
        listView.setOnItemClickListener(new OnItemClickListener(){
        	public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
        		info getObject = mlistInfo.get(position);	//通过position获取所点击的对象
        		int infoId = getObject.getId();	//获取信息id
        		String infoTitle = getObject.getTitle();	//获取信息标题
        		String infoDetails = getObject.getDetails();	//获取信息详情
        		
        		//Toast显示测试
        		Toast.makeText(ListViewActivity.this, "信息ID:"+infoId,Toast.LENGTH_SHORT).show();
        	}
        });
        
        //长按菜单显示
        listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
        	public void onCreateContextMenu(ContextMenu conMenu, View view , ContextMenuInfo info) {
        		conMenu.setHeaderTitle("菜单");
        		conMenu.add(0, 0, 0, "条目一");
        		conMenu.add(0, 1, 1, "条目二");
        		conMenu.add(0, 2, 2, "条目三");
        	}
        });
        
    }
	//长按菜单处理函数
	public boolean onContextItemSelected(MenuItem aItem) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)aItem.getMenuInfo();
        switch (aItem.getItemId()) {
             case 0:
            	 Toast.makeText(ListViewActivity.this, "你点击了条目一",Toast.LENGTH_SHORT).show();
            	 return true;
             case 1:
            	 Toast.makeText(ListViewActivity.this, "你点击了条目二",Toast.LENGTH_SHORT).show();
             
            	 return true;
             case 2:
            	 Toast.makeText(ListViewActivity.this, "你点击了条目三",Toast.LENGTH_SHORT).show();
            	 return true;
        }
		return false;
   }
	
	
    public class ListViewAdapter extends BaseAdapter {  
        View[] itemViews;
  
        public ListViewAdapter(List<info> mlistInfo) {
			// TODO Auto-generated constructor stub
            itemViews = new View[mlistInfo.size()];            
            for(int i=0;i<mlistInfo.size();i++){
            	info getInfo=(info)mlistInfo.get(i);	//获取第i个对象
            	//调用makeItemView，实例化一个Item
            	itemViews[i]=makeItemView(
            			getInfo.getTitle(), getInfo.getDetails(),getInfo.getAvatar()
            			);
            }
		}

		public int getCount() {
            return itemViews.length;  
        }
  
        public View getItem(int position) {  
            return itemViews[position];  
        }  
  
        public long getItemId(int position) {  
            return position;  
        }
        
        //绘制Item的函数
        private View makeItemView(String strTitle, String strText, int resId) {  
            LayoutInflater inflater = (LayoutInflater) ListViewActivity.this  
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
  
            // 使用View的对象itemView与R.layout.item关联
            View itemView = inflater.inflate(R.layout.item, null);
            
            // 通过findViewById()方法实例R.layout.item内各组件
            TextView title = (TextView) itemView.findViewById(R.id.title);  
            title.setText(strTitle);	//填入相应的值
            TextView text = (TextView) itemView.findViewById(R.id.info);  
            text.setText(strText);  
            ImageView image = (ImageView) itemView.findViewById(R.id.img);  
            image.setImageResource(resId);
            
            return itemView;  
        }
  
        
		public View getView(int position, View convertView, ViewGroup parent) {  
            if (convertView == null)  
                return itemViews[position];  
            return convertView;
        }

    }	
	
	public void setInfo(){
		mlistInfo.clear();
		int i=0;
		while(i<10){
			info information = new info();
			information.setId(1000+i);
			information.setTitle("标题"+i);
			information.setDetails("详细信息"+i);
			information.setAvatar(R.drawable.i2);
			
			mlistInfo.add(information);	//将新的info对象加入到信息列表中
			i++;
		}
	}

}

