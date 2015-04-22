package com.example.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;


public class MyDialog1 extends Dialog 
{    
	Context context;    
	
	public MyDialog1(Context context) 
	{        
		super(context);        
		// TODO Auto-generated constructor stub        
		this.context = context;    
	}    
	public MyDialog1(Context context, int theme)
	{       
		super(context, theme);        
		this.context = context;    
	}    
	@Override    
	protected void onCreate(Bundle savedInstanceState) 
	{        
		// TODO Auto-generated method stub        
		super.onCreate(savedInstanceState);        
		this.setContentView(R.layout.dialog);    
	}
}
