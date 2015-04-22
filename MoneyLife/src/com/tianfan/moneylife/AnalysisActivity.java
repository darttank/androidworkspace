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
    //收入类TextView ，包括工资收入、股票收入，其他收入
	TextView month_income_persent,month_pay_income_persent,month_stock_income_persent,
	         month_another_income_persent;
	//收入类：工资...
	float money_month_income_persent=0,money_month_pay_income_persent=0,money_month_stock_income_persent=0, money_month_another_income_persent=0;
	
	//支出类Textview，包括总支出、日常购物、交际送礼、餐饮开销、购置衣物、娱乐开销、网费话费、交通出行、水电煤气、其他花费
	TextView month_out_persent,month_out_shopping_persent,month_out_gift_persent,month_out_eatting_persent,month_out_clothes_persent,
	month_out_play_persent,month_out_internet_persent,month_out_traffic_persent,month_out_daily_persent,month_out_another_persent;
	//支出类
	float money_month_out_persent=0,money_month_out_shopping_persent=0,money_month_out_gift_persent=0,money_month_out_eatting_persent=0,money_month_out_clothes_persent=0,
	money_month_out_play_persent=0,money_month_out_internet_persent=0,money_month_out_traffic_persent=0,money_month_out_daily_persent=0,money_month_out_another_persent=0;
	
	//本月总结余
	TextView month_money_left;
	float MoneyMonthLeft=0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_analysis);
		//获得 收入以及收入类型的实例
		month_income_persent = (TextView) findViewById(R.id.month_income_persent);
		month_pay_income_persent = (TextView) findViewById(R.id.month_pay_income_persent);
		month_stock_income_persent = (TextView) findViewById(R.id.month_stock_income_persent);
		month_another_income_persent = (TextView) findViewById(R.id.month_another_income_persent);
	
		//支出实例化
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
		
		
		//收入类
		fillIncomeList();
		
		//支出类
		fillOutList();
		
		
	}
	
	
	private void fillIncomeList()
	{
		    Calendar localCalendar = Calendar.getInstance();
		    int year = localCalendar.get(Calendar.YEAR);
		    int month = localCalendar.get(Calendar.MONTH)+1;
			String str1=new String(year+"-"+month);
			String str;
			
		    //获得收入
			MyPackage pack=new MyPackage(this);
			List<TradeClass> List=pack.getAlltrade();
			for(TradeClass con:List){
				str = con.gettime();
				str=str.substring(0, str.lastIndexOf('-'));
				if(str1.equals(str)){
				
					
					if(con.getPocketType().equals("其他收入")){
						money_month_another_income_persent +=con.getMoney();
						
					}else if(con.getPocketType().equals("工资收入")){
						money_month_pay_income_persent +=con.getMoney();
						
					}else if(con.getPocketType().equals("股票收入")){
						money_month_stock_income_persent +=con.getMoney();
						
					}
					
					
					}
				}
		
			
			
			//月工资收入
			money_month_income_persent = 
					money_month_pay_income_persent +
					money_month_stock_income_persent +
					money_month_another_income_persent;
			
			month_income_persent.setText("本月共收入："+(money_month_income_persent)+"元");
			month_pay_income_persent.setText("本月工资收入："+(money_month_pay_income_persent)+"元");
			month_stock_income_persent.setText("本月股票收入："+(money_month_stock_income_persent)+"元");
			month_another_income_persent.setText("本月其他收入："+(money_month_another_income_persent)+"元");
			
			
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
							if(con.getPocketType().equals("日常购物")){
								
								money_month_out_shopping_persent += con.getMoney();
								
							}else if(con.getPocketType().equals("交际送礼")){
								
								money_month_out_gift_persent += con.getMoney();
								
							}else if(con.getPocketType().equals("餐饮开销")){
								
								
								money_month_out_eatting_persent +=con.getMoney();
								
							}else if(con.getPocketType().equals("购置衣物")){

								money_month_out_clothes_persent +=con.getMoney();
								
							}else if(con.getPocketType().equals("娱乐开销")){
								
								money_month_out_play_persent +=con.getMoney();
								
							}else if(con.getPocketType().equals("水电煤气")){
								
								money_month_out_daily_persent +=con.getMoney();
								
							}else if(con.getPocketType().equals("网费话费")){
								
								money_month_out_internet_persent+=con.getMoney();
								
							}else if(con.getPocketType().equals("交通出行")){
								
								money_month_out_traffic_persent +=con.getMoney();
								
							}else if(con.getPocketType().equals("其他花费")){
								
								money_month_out_another_persent +=con.getMoney();
								
							}else{
								map.put("icon",R.drawable.qita);
							}
							
						
									
							
							
						}
					}
				//本月总支出
				money_month_out_persent = money_month_out_shopping_persent+
						money_month_out_gift_persent+
						money_month_out_eatting_persent+
						money_month_out_clothes_persent+
						money_month_out_play_persent+
						money_month_out_internet_persent+
						money_month_out_traffic_persent+
						money_month_out_daily_persent+
						money_month_out_another_persent;
				//把支出详细数据显示到TextView上去
				//总支出
				month_out_persent.setText("总支出："+money_month_out_persent+" RMB");	
				
				month_out_gift_persent.setText("交际送礼："+money_month_out_gift_persent+" RMB");	
				month_out_eatting_persent.setText("餐饮开销："+money_month_out_eatting_persent+" RMB");	
				month_out_shopping_persent.setText("日常购物："+money_month_out_shopping_persent+" RMB");	
				
				month_out_clothes_persent.setText("购置衣物："+money_month_out_clothes_persent+" RMB");	
				month_out_play_persent.setText("娱乐开销："+money_month_out_play_persent+"RMB");	
				month_out_internet_persent.setText("网费话费："+money_month_out_internet_persent+" RMB");	
				
				month_out_traffic_persent.setText("交通出行："+money_month_out_traffic_persent+" RMB");	
				month_out_daily_persent.setText("水电煤气："+money_month_out_daily_persent+" RMB");	
				month_out_another_persent.setText("其他花费："+money_month_out_another_persent+" RMB");	
			
				MoneyMonthLeft =money_month_income_persent- money_month_out_persent;
				
				if(MoneyMonthLeft>0){
					month_money_left.setText("本月结余："+MoneyMonthLeft+" RMB");
				}else{
					month_money_left.setText("本月负债："+MoneyMonthLeft+" RMB");
				}
				
	}


	
}
