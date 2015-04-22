package com.tianfan.moneylife;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianfan.bean.TradeClass;
import com.tianfan.control.Adapter_TD;

import android.os.Bundle;
import android.provider.CalendarContract.Colors;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BudgetActivity extends Activity {
	
	SharedPreferences share;

	EditText budget_by_day, budget_by_month, budget_by_year;
	Button budget_setting, budget_change;

	TextView out_by_day, out_by_month, out_by_year;
	TextView out_by_today_account, out_by_month_account, out_by_year_account;
	float todaymenoy = 0;
	float monthmenoy = 0;
	float yearmenoy = 0;

	static float budget_by_day_text = 0, budget_by_month_text = 0,
			budget_by_year_text = 0;
	
	String budget_day="",budget_month="",budget_year="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_budget);

		budget_by_day = (EditText) findViewById(R.id.budget_by_day);
		budget_by_month = (EditText) findViewById(R.id.budget_by_month);
		budget_by_year = (EditText) findViewById(R.id.budget_by_year);

		budget_setting = (Button) findViewById(R.id.budget_setting);
		budget_change = (Button) findViewById(R.id.budget_change);

		out_by_day = (TextView) findViewById(R.id.out_by_day);
		out_by_month = (TextView) findViewById(R.id.out_by_month);
		out_by_year = (TextView) findViewById(R.id.out_by_year);

		out_by_today_account = (TextView) findViewById(R.id.out_by_today_account);
		out_by_month_account = (TextView) findViewById(R.id.out_by_month_account);
		out_by_year_account = (TextView) findViewById(R.id.out_by_year_account);

		
		
		//�������SharePreference �е�����
		SharedPreferences share=getSharedPreferences("bugdget",MODE_PRIVATE);
		
		budget_by_day_text=share.getFloat("budget_day", (float) 0.0);
		budget_by_month_text=share.getFloat("budget_month", (float) 0.0);
		budget_by_year_text=share.getFloat("budget_year", (float) 0.0);
		
		budget_by_day.setText(share.getFloat("budget_day", (float) 0.0)+"");
		budget_by_month.setText(share.getFloat("budget_month", (float) 0.0)+"");
		budget_by_year.setText(share.getFloat("budget_year", (float) 0.0)+"");
	
		
		budget_by_day.setEnabled(false);
		budget_by_month.setEnabled(false);
		budget_by_year.setEnabled(false);
		
		// ���ջ��ѵĽ��
				GetTodayBill();

				// ���»��ѵĽ��
				GetMonthBill();

				// ���껨�ѵĽ��
				GetYearBill();

				

				// ����Ԥ���Ƿ񳬳����ý��
				Budget_account();
				
				
				
				
		
		budget_setting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// ����Ԥ��,�ı����б��붼Ҫ�����ݣ�������ð�ť֮�����������ð�ťʧЧ
				if ((budget_by_day.getText().toString() != "")
						&& (budget_by_month.getText().toString() != "")
						&& (budget_by_year.getText().toString() != "")) {

					Toast.makeText(BudgetActivity.this, "OK", 500).show();

					Log.i("tian", budget_by_day.getText().toString());
					Log.i("tian", budget_by_month.getText().toString());
					Log.i("tian", budget_by_year.getText().toString());

					budget_by_day_text = Float.parseFloat(budget_by_day
							.getText().toString());
					budget_by_month_text = Float.parseFloat(budget_by_month
							.getText().toString());
					budget_by_year_text = Float.parseFloat(budget_by_year
							.getText().toString());
				
					SharedPreferences share=getSharedPreferences("bugdget",MODE_PRIVATE);
					
					Editor editor =share.edit();
					editor.putFloat("budget_day", budget_by_day_text);
					editor.putFloat("budget_month", budget_by_month_text);
					editor.putFloat("budget_year", budget_by_year_text);
					editor.commit();
					
					
					budget_by_day.setEnabled(false);
					budget_by_month.setEnabled(false);
					budget_by_year.setEnabled(false);

				} else {
					Toast.makeText(BudgetActivity.this, "����Ϊ��", 500).show();
				}

				Budget_account();
			}
		});

		budget_change.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				budget_by_day.setEnabled(true);
				budget_by_month.setEnabled(true);
				budget_by_year.setEnabled(true);
			}
		});
	}

	private void GetTodayBill() {

		// List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		// float todaymenoy=0;
		MyPackage pack = new MyPackage(this);
		List<TradeClass> List = pack.getAlltrade();
		// bill_array=new String[List.size()];
		int i = 0;
		Calendar localCalendar = Calendar.getInstance();
		int year = localCalendar.get(Calendar.YEAR);
		int month = localCalendar.get(Calendar.MONTH) + 1;
		int day = localCalendar.get(Calendar.DAY_OF_MONTH);
		String str1 = new String(year + "-" + month + "-" + day);
		String str;
		for (TradeClass con : List) {
			str = con.gettime();
			if (str1.equals(str)) {
				// bill_array[i]=con.getId()+"|*****|"+con.getMoney()+"\n"+con.getPocketType()+"|****|"+con.gettime();
				todaymenoy += con.getMoney();
				i++;
			}
		}

		out_by_day.setText("���չ����ѣ�" + (todaymenoy) + "Ԫ");

		if (i == 0) {
			Toast.makeText(getApplicationContext(), "��������û������Ŷ��",
					Toast.LENGTH_SHORT);
		}
	}

	private void GetMonthBill() {
		MyPackage pack = new MyPackage(this);
		List<TradeClass> List = pack.getAlltrade();
		int i = 0;
		Calendar localCalendar = Calendar.getInstance();
		int year = localCalendar.get(Calendar.YEAR);
		int month = localCalendar.get(Calendar.MONTH) + 1;
		// int day = localCalendar.get(Calendar.DAY_OF_MONTH);
		String str1 = new String(year + "-" + month);
		String str;
		for (TradeClass con : List) {
			str = con.gettime();
			Log.i("tian_month", str);
			if (str.substring(0, 7).equals(str1)) {
				// bill_array[i]=con.getId()+"|*****|"+con.getMoney()+"\n"+con.getPocketType()+"|****|"+con.gettime();
				monthmenoy += con.getMoney();
				i++;
			}
		}

		out_by_month.setText("���¹����ѣ�" + (monthmenoy) + "Ԫ");

		if (i == 0) {
			Toast.makeText(getApplicationContext(), "��������û������Ŷ��",
					Toast.LENGTH_SHORT);
		}

	}

	private void GetYearBill() {
		MyPackage pack = new MyPackage(this);
		List<TradeClass> List = pack.getAlltrade();
		// bill_array=new String[List.size()];
		int i = 0;
		Calendar localCalendar = Calendar.getInstance();
		int year = localCalendar.get(Calendar.YEAR);
		// int month = localCalendar.get(Calendar.MONTH)+1;
		// int day = localCalendar.get(Calendar.DAY_OF_MONTH);
		String str1 = new String(year + "");
		String str;
		for (TradeClass con : List) {
			str = con.gettime();
			Log.i("tian_year", str);
			if (str.substring(0, 4).equals(str1)) {

				// bill_array[i]=con.getId()+"|*****|"+con.getMoney()+"\n"+con.getPocketType()+"|****|"+con.gettime();
				yearmenoy += con.getMoney();

				i++;
			}
		}

		out_by_year.setText("���깲���ѣ�" + (yearmenoy) + "Ԫ");

		if (i == 0) {
			Toast.makeText(getApplicationContext(), "��������û������Ŷ��",
					Toast.LENGTH_SHORT);
		}
	}

	private void Budget_account() {
		if ((budget_by_day_text - todaymenoy > 0)) {
			out_by_today_account.setText("����û��Ԥ�㣬����Ŷ��������ƣ�");
		} else {
			out_by_today_account.setText("���ճ�Ԥ����Ϊ��"
					+ (budget_by_day_text - todaymenoy) + "");
			
		}if ((budget_by_month_text - monthmenoy > 0)) {
			out_by_month_account.setText("����û��Ԥ�㣬����Ŷ��������ƣ�");
		} else {
			out_by_month_account.setText("���³�Ԥ����Ϊ��"
					+ (budget_by_month_text - monthmenoy) + "");
		}
		if ((budget_by_year_text - yearmenoy > 0)) {
			out_by_year_account.setText("����û��Ԥ�㣬����Ŷ��������ƣ�");
		} else {
			out_by_year_account.setText("���곬Ԥ����Ϊ��"
					+ (budget_by_year_text - yearmenoy) + "");
		}

	}
	
	

	

}
