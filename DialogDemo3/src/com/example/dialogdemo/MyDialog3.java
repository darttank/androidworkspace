package com.example.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyDialog3 extends Dialog implements OnClickListener{
		
	Context context;    
//	private Button ok,cancle;
	private Button cancle;
	private ListenerThree listener;
	
	public MyDialog3(Context context) 
	{        
		super(context);        
		// TODO Auto-generated constructor stub        
		this.context = context;    
	}    
	public MyDialog3(Context context, int theme,ListenerThree listener)
	{       
		super(context, theme);       
		this.context = context;
		this.listener = listener;
	}    
	
	public MyDialog3(Context context, int theme)
	{       
		super(context, theme);       
		this.context = context;
	}
   	
	public interface ListenerThree{ 
        public void onClick(View view); 
    }
	//设置应该响应onClick事件的接口
	public void SetListener(ListenerThree listener){
		this.listener = listener;
	}
	
	protected void onCreate(Bundle savedInstanceState) 
	{        
		// TODO Auto-generated method stub        
		super.onCreate(savedInstanceState);        
		this.setContentView(R.layout.dialog);   
		init();
	}
	
	public void init()
	{
		//ok = (Button) findViewById(R.id.dialog_button_ok);	    
		cancle = (Button) findViewById(R.id.dialog_button_cancle);
		//ok.setOnClickListener(this);
		cancle.setOnClickListener(this);
	}
	
	public void onClick(View v) { 
		//调用本类里面的接口中的onClick,就是所谓的接口回调吧
        listener.onClick(v); 
    }
}


