package com.tianfan.moneylife;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianfan.bean.TradeClass;
import com.tianfan.control.Adapter_LS;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class AnalysisActivity extends Activity {
    //������TextView �������������롢��Ʊ���룬��������
	TextView month_income_persent,month_pay_income_persent,month_stock_income_persent,
	         month_another_income_persent;
	//�����ࣺ����...
	float money_month_income_persent=0,money_month_pay_income_persent=0,money_month_stock_income_persent=0, money_month_another_income_persent=0;
	
	//֧����Textview��������֧�����ճ�����������񡢲�������������������ֿ��������ѻ��ѡ���ͨ���С�ˮ��ú������������
	TextView month_out_persent,month_out_shopping_persent,month_out_gift_persent,month_out_eatting_persent,month_out_clothes_persent,
	month_out_play_persent,month_out_internet_persent,month_out_traffic_persent,month_out_daily_persent,month_out_another_persent;
	//֧����
	float money_month_out_persent=0,money_month_out_shopping_persent=0,money_month_out_gift_persent=0,money_month_out_eatting_persent=0,money_month_out_clothes_persent=0,
	money_month_out_play_persent=0,money_month_out_internet_persent=0,money_month_out_traffic_persent=0,money_month_out_daily_persent=0,money_month_out_another_persent=0;
	
	//�����ܽ���
	TextView month_money_left;
	float MoneyMonthLeft=0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_analysis);
		//��� �����Լ��������͵�ʵ��
		month_income_persent = (TextView) findViewById(R.id.month_income_persent);
		month_pay_income_persent = (TextView) findViewById(R.id.month_pay_income_persent);
		month_stock_income_persent = (TextView) findViewById(R.id.month_stock_income_persent);
		month_another_income_persent = (TextView) findViewById(R.id.month_another_income_persent);
	
		//֧��ʵ����
		month_out_persent = (TextView) findViewById(R.id.month_out_persent);
		month_out_shopping_persent = (TextView) findViewById(R.id.month_out_shopping_persent);
		month_out_gift_persent = (TextView) findViewById(R.id.month_out_gift_persent);
		month_out_eatting_persent = (TextView) findViewById(R.id.month_out_eatting_persent);
		
		month_out_clothes_persent = (TextView) findViewById(R.id.month_out_clothes_persent);
		month_out_play_persent = (TextView) findViewById(R.id.month_out_play_persent);
		month_out_internet_persent = (TextView) findViewById(R.id.month_out_internet_persent);
		
		month_out_traffic_persent = (TextView) findViewById(R.id.month_out_traffic_persent);
		month_out_daily_persent = (TextView) findViewById(R.id.month_out_daily_persent);
		month_out_another_persent = (TextView) findViewById(R.id.month_out_another_persent);
		
		
		month_money_left = (TextView)findViewById(R.id.month_money_left);
		
		
		//������
		fillIncomeList();
		
		//֧����
		fillOutList();
		
		
	}
	
	
	private void fillIncomeList()
	{
		    Calendar localCalendar = Calendar.getInstance();
		    int year = localCalendar.get(Calendar.YEAR);
		    int month = localCalendar.get(Calendar.MONTH)+1;
			String str1=new String(year+"-"+month);
			String str;
			
		    //�������
			MyPackage pack=new MyPackage(this);
			List<TradeClass> List=pack.getAlltrade();
			for(TradeClass con:List){
				str = con.gettime();
				str=str.substring(0, str.lastIndexOf('-'));
				if(str1.equals(str)){
				
					
					if(con.getPocketType().equals("��������")){
						money_month_another_income_persent +=con.getMoney();
						
					}else if(con.getPocketType().equals("��������")){
						money_month_pay_income_persent +=con.getMoney();
						
					}else if(con.getPocketType().equals("��Ʊ����")){
						money_month_stock_income_persent +=con.getMoney();
						
					}
					
					
					}
				}
		
			
			
			//�¹�������
			money_month_income_persent = 
					money_month_pay_income_persent +
					money_month_stock_income_persent +
					money_month_another_income_persent;
			
			month_income_persent.setText("���¹����룺"+(money_month_income_persent)+"Ԫ");
			month_pay_income_persent.setText("���¹������룺"+(money_month_pay_income_persent)+"Ԫ");
			month_stock_income_persent.setText("���¹�Ʊ���룺"+(money_month_stock_income_persent)+"Ԫ");
			month_another_income_persent.setText("�����������룺"+(money_month_another_income_persent)+"Ԫ");
			
			
	}
	
	
	private void fillOutList(){
		
			    Calendar localCalendar = Calendar.getInstance();
			    int year = localCalendar.get(Calendar.YEAR);
			    int month = localCalendar.get(Calendar.MONTH)+1;
				String str1=new String(year+"-"+month);
				String str;
				List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
				
				MyPackage pack=new MyPackage(this);
				List<TradeClass> List=pack.getAlltrade();
				for(TradeClass con:List){
					str = con.gettime();
					str=str.substring(0, str.lastIndexOf('-'));
					if(str1.equals(str)){
						//money_month_out_persent+=con.getMoney();
							Map<String,Object> map=new HashMap<String,Object>();
							map.put("_id", con.getId());
							map.put("money", ""+con.getMoney());
							if(con.getPocketType().equals("�ճ�����")){
								
								money_month_out_shopping_persent += con.getMoney();
								
							}else if(con.getPocketType().equals("��������")){
								
								money_month_out_gift_persent += con.getMoney();
								
							}else if(con.getPocketType().equals("��������")){
								
								
								money_month_out_eatting_persent +=con.getMoney();
								
							}else if(con.getPocketType().equals("��������")){

								money_month_out_clothes_persent +=con.getMoney();
								
							}else if(con.getPocketType().equals("���ֿ���")){
								
								money_month_out_play_persent +=con.getMoney();
								
							}else if(con.getPocketType().equals("ˮ��ú��")){
								
								money_month_out_daily_persent +=con.getMoney();
								
							}else if(con.getPocketType().equals("���ѻ���")){
								
								money_month_out_internet_persent+=con.getMoney();
								
							}else if(con.getPocketType().equals("��ͨ����")){
								
								money_month_out_traffic_persent +=con.getMoney();
								
							}else if(con.getPocketType().equals("��������")){
								
								money_month_out_another_persent +=con.getMoney();
								
							}else{
								map.put("icon",R.drawable.qita);
							}
							
						
									
							
							
						}
					}
				//������֧��
				money_month_out_persent = money_month_out_shopping_persent+
						money_month_out_gift_persent+
						money_month_out_eatting_persent+
						money_month_out_clothes_persent+
						money_month_out_play_persent+
						money_month_out_internet_persent+
						money_month_out_traffic_persent+
						money_month_out_daily_persent+
						money_month_out_another_persent;
				//��֧����ϸ������ʾ��TextView��ȥ
				//��֧��
				month_out_persent.setText("��֧����"+money_month_out_persent+" RMB");	
				
				month_out_gift_persent.setText("��������"+money_month_out_gift_persent+" RMB");	
				month_out_eatting_persent.setText("����������"+money_month_out_eatting_persent+" RMB");	
				month_out_shopping_persent.setText("�ճ����"+money_month_out_shopping_persent+" RMB");	
				
				month_out_clothes_persent.setText("�������"+money_month_out_clothes_persent+" RMB");	
				month_out_play_persent.setText("���ֿ�����"+money_month_out_play_persent+"RMB");	
				month_out_internet_persent.setText("���ѻ��ѣ�"+money_month_out_internet_persent+" RMB");	
				
				month_out_traffic_persent.setText("��ͨ���У�"+money_month_out_traffic_persent+" RMB");	
				month_out_daily_persent.setText("ˮ��ú����"+money_month_out_daily_persent+" RMB");	
				month_out_another_persent.setText("�������ѣ�"+money_month_out_another_persent+" RMB");	
			
				MoneyMonthLeft =money_month_income_persent- money_month_out_persent;
				
				if(MoneyMonthLeft>0){
					month_money_left.setText("���½��ࣺ"+MoneyMonthLeft+" RMB");
				}else{
					month_money_left.setText("���¸�ծ��"+MoneyMonthLeft+" RMB");
				}
				
	}


	
}
