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
	ListView listView;	//����һ��ListView����
	private List<info> mlistInfo = new ArrayList<info>();  //����һ��list����̬�洢Ҫ��ʾ����Ϣ
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);       
        
        listView=(ListView)this.findViewById(R.id.listView);	//��listView�벼�ֶ������
        
        setInfo();	//����Ϣ��ֵ��������������
        
        listView.setAdapter(new ListViewAdapter(mlistInfo));
        
        //����Item�ĵ���¼�
        listView.setOnItemClickListener(new OnItemClickListener(){
        	public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
        		info getObject = mlistInfo.get(position);	//ͨ��position��ȡ������Ķ���
        		int infoId = getObject.getId();	//��ȡ��Ϣid
        		String infoTitle = getObject.getTitle();	//��ȡ��Ϣ����
        		String infoDetails = getObject.getDetails();	//��ȡ��Ϣ����
        		
        		//Toast��ʾ����
        		Toast.makeText(ListViewActivity.this, "��ϢID:"+infoId,Toast.LENGTH_SHORT).show();
        	}
        });
        
        //�����˵���ʾ
        listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
        	public void onCreateContextMenu(ContextMenu conMenu, View view , ContextMenuInfo info) {
        		conMenu.setHeaderTitle("�˵�");
        		conMenu.add(0, 0, 0, "��Ŀһ");
        		conMenu.add(0, 1, 1, "��Ŀ��");
        		conMenu.add(0, 2, 2, "��Ŀ��");
        	}
        });
        
    }
	//�����˵�������
	public boolean onContextItemSelected(MenuItem aItem) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)aItem.getMenuInfo();
        switch (aItem.getItemId()) {
             case 0:
            	 Toast.makeText(ListViewActivity.this, "��������Ŀһ",Toast.LENGTH_SHORT).show();
            	 return true;
             case 1:
            	 Toast.makeText(ListViewActivity.this, "��������Ŀ��",Toast.LENGTH_SHORT).show();
             
            	 return true;
             case 2:
            	 Toast.makeText(ListViewActivity.this, "��������Ŀ��",Toast.LENGTH_SHORT).show();
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
            	info getInfo=(info)mlistInfo.get(i);	//��ȡ��i������
            	//����makeItemView��ʵ����һ��Item
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
        
        //����Item�ĺ���
        private View makeItemView(String strTitle, String strText, int resId) {  
            LayoutInflater inflater = (LayoutInflater) ListViewActivity.this  
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
  
            // ʹ��View�Ķ���itemView��R.layout.item����
            View itemView = inflater.inflate(R.layout.item, null);
            
            // ͨ��findViewById()����ʵ��R.layout.item�ڸ����
            TextView title = (TextView) itemView.findViewById(R.id.title);  
            title.setText(strTitle);	//������Ӧ��ֵ
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
			information.setTitle("����"+i);
			information.setDetails("��ϸ��Ϣ"+i);
			information.setAvatar(R.drawable.i2);
			
			mlistInfo.add(information);	//���µ�info������뵽��Ϣ�б���
			i++;
		}
	}

}

