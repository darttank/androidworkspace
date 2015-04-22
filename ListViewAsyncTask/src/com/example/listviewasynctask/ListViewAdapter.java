package com.example.listviewasynctask;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter implements ListAdapter 
{

    private ArrayList<Person> data;
    private int id;
    private Context context;
    private LayoutInflater inflater;
    public ListViewAdapter(int item, MainActivity mainActivity,ArrayList<Person> data) 
    {
        this.data=data;
        this.context=mainActivity;
        this.id=item;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() 
    {
        return data.size();
    }

    @Override
    public Object getItem(int position) 
    {
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2)
    {
        TextView name=null;
        TextView sex=null;
        TextView age=null;
        ImageView img=null;
        if(view==null)
        {
            view=inflater.inflate(id, null);
            name=(TextView) view.findViewById(R.id.PersonName);
            sex=(TextView) view.findViewById(R.id.PersonSex);
            age=(TextView) view.findViewById(R.id.PersonAge);
            img=(ImageView) view.findViewById(R.id.Personimage);
            //保存view对象到ObjectClass类中
            view.setTag(new ObjectClass(name,sex,age,img));
        }
        else
        {
            //得到保存的对象
            ObjectClass objectclass=(ObjectClass) view.getTag();
            name=objectclass.name;
            sex=objectclass.sex;
            age=objectclass.age;
            img=objectclass.img;
        }
        
        Person person=(Person) data.get(position);
        //帮数据绑定到控件上
        name.setText(person.getName().toString());
        sex.setText("性别："+person.getSex().toString());
        age.setText("年龄："+String.valueOf(person.getAge()));
        //加载图片资源
        LoadImage(img,person.getPath());
        return view;
        
    }

    private void LoadImage(ImageView img, String path) 
    {
        //异步加载图片资源
        AsyncTaskImageLoad async=new AsyncTaskImageLoad(img);
        //执行异步加载，并把图片的路径传送过去
        async.execute(path);
        
    }

    private final class ObjectClass
    {
        TextView name=null;
        TextView sex=null;
        TextView age=null;
        ImageView img=null;
        public ObjectClass(TextView name, TextView sex, TextView age,ImageView img) 
        {
            this.name=name;
            this.sex=sex;
            this.age=age;
            this.img=img;
        }
    }

	
    
}