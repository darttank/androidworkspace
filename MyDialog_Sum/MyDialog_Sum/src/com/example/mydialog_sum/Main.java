package com.example.mydialog_sum;

import com.example.mydialog_sum.MyDialog3.ListenerThree;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener{
	
	Button bt1,bt2,bt3,bt4,bt5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}
	
	public void init()
	{
		bt1 = (Button)findViewById(R.id.bt1);
		bt2 = (Button)findViewById(R.id.bt2);
		bt3 = (Button)findViewById(R.id.bt3);
		bt4 = (Button)findViewById(R.id.bt4);
		bt5 = (Button)findViewById(R.id.bt5);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);
		bt5.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.bt1:
			Dialog dialog1 = new MyDialog1(Main.this,R.style.MyDialog);
			dialog1.show();
			break;
		case R.id.bt2:
			Dialog dialog2 = new Dialog(Main.this, R.style.MyDialog);
			//��������ContentView                 
			dialog2.setContentView(R.layout.dialog);
			dialog2.show();
			break;
		case R.id.bt3:
			final MyDialog3 dialog3 = new MyDialog3(Main.this,R.style.MyDialog);
			ListenerThree listener3 = new ListenerThree()
			{
				@Override 
		         public void onClick(View view) 
		         {
		        	 switch(view.getId()){
		        	 case R.id.dialog_button_ok:
		        		 Toast.makeText(Main.this, "�����OK Button", Toast.LENGTH_LONG).show();
		        		 break;
		        	 case R.id.dialog_button_cancle:
		        		 dialog3.dismiss();
		        		 Toast.makeText(Main.this, "�����Cancle Button", Toast.LENGTH_LONG).show();
		        		 break;
		        	 }
		         }
			};
			dialog3.SetListener(listener3);
			//����Dialog��������ʽ����
			//����ǽ�Dialog�ƶ���ָ��λ��
			Window window = dialog3.getWindow();
			LayoutParams para = new LayoutParams();
			para.x = 80;//����x����
			para.y = -200;//����y����
			window.setAttributes(para);
			dialog3.setCanceledOnTouchOutside(true);
			//���õ��Dialog�ⲿ��������ر�Dialog
			dialog3.show();
			break;
		case R.id.bt4:
			Dialog dialog4 = buildDialog(Main.this,R.style.MyDialog,4);
			dialog4.setCanceledOnTouchOutside(true);//���õ��Dialog�ⲿ��������ر�Dialog
			dialog4.show();
			break;
		case R.id.bt5:
			Dialog dialog5 = buildDialog(Main.this,R.style.MyDialog,5);
			dialog5.setCanceledOnTouchOutside(false);//���õ��Dialog�ⲿ��������ر�Dialog
			dialog5.show();
			break;
		}
	}

	Dialog buildDialog(Context context,int theme,int id) {		
		//��layout��ΪView
		LayoutInflater inflater = (LayoutInflater) context 
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.dialog, null);
		Button ok = (Button) view.findViewById(R.id.dialog_button_ok);
		Button cancle = (Button) view.findViewById(R.id.dialog_button_cancle);				
		if(id==4)
		{	
			Builder dialogBuilder = new AlertDialog.Builder(context,theme);
			//dialogBuilder.setView(view);//������䵼�³������³��ֺ�ɫ�Ĵ���		
			final AlertDialog dialog4 = dialogBuilder.create();
			OnClickListener listener4 = new OnClickListener()
			{
				public void onClick(View view) 
		         {
		        	 switch(view.getId()){
		        	 case R.id.dialog_button_ok:
		        		 Toast.makeText(Main.this, "�����OK Button", Toast.LENGTH_LONG).show();
		        		 break;
		        	 case R.id.dialog_button_cancle:
		        		 dialog4.dismiss();
		        		 break;				        	 
		        	 }
		         }
			};
			ok.setOnClickListener(listener4);
			cancle.setOnClickListener(listener4);
			dialog4.setIcon(Color.parseColor("#FFFFFF"));		 
			dialog4.setView(view, 0, 0, 0, 0);//�ܹ��������ߣ����ǲ��������߿�
			return dialog4;
		}
		if(id==5)
		{
			final Dialog dialog5 = new Dialog(context,theme);
			//dialog.setContentView(R.layout.dialog);
			dialog5.setContentView(view);
			dialog5.setCancelable(true);
			OnClickListener listener5 = new OnClickListener()
			{
				public void onClick(View view) 
		         {
		        	 switch(view.getId()){
		        	 case R.id.dialog_button_ok:
		        		 Toast.makeText(Main.this, "�����OK Button", Toast.LENGTH_LONG).show();
		        		 break;
		        	 case R.id.dialog_button_cancle:
		        		 dialog5.dismiss();
		        		 break;				        	 
		        	 }
		         }
			};
			ok.setOnClickListener(listener5);
			cancle.setOnClickListener(listener5);
			return dialog5;
		}
		else{
			return null;
		}
	}
}
