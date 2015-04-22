package com.tianfan.moneylife;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianfan.bean.ConsumeClass;
import com.tianfan.bean.IncomeClass;
import com.tianfan.bean.TradeClass;
import com.tianfan.bean.typeClass;
import com.tianfan.control.Adapter_LS;
import com.tianfan.control.Adapter_TD;
import com.tianfan.db.DBHelper;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class QueryByDateActivity extends Activity {
	private DBHelper dbhelper;
	private SQLiteDatabase db;
	 private ListView listView_all;
	  private TextView textView_all;
	  private Adapter_LS myadapter;
	  private Button btn_search;
	  Map<Integer, Boolean> localmap;
	private TextView add_start_day,add_end_day ;
	Cursor cu;
	DatePickerDialog.OnDateSetListener OnDateSetListener,OnDateSetListener2 ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query_by_date);
		
		
		    this.add_start_day = ((TextView)findViewById(R.id.add_start_day));
		    
		    this.add_end_day = ((TextView)findViewById(R.id.add_end_day));
		    
		    this.listView_all =(ListView)findViewById(R.id.listView_all);
		    this.textView_all =(TextView)findViewById(R.id.textView_all);
		    
		    this.add_start_day.setOnClickListener(new DateOnClick());
		    
		    this.add_end_day.setOnClickListener(new DateOnClick2());
		    
		    this.btn_search=(Button)findViewById(R.id.btn_search);
		    
	       
		  //搜索日期范围之内的所有账单
		    btn_search.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
					//获得所有的账单的list 
					fillList();
				}
			}) ; 
		    
    OnDateSetListener = new DatePickerDialog.OnDateSetListener()
		{
		    public void onDateSet(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3)
		    {
		    	//"起始日期："
		    		QueryByDateActivity.this.add_start_day.setText(paramInt1 + "-" + (paramInt2 + 1) + "-" + paramInt3);
		    		
		    }
		};
		

	    OnDateSetListener2 = new DatePickerDialog.OnDateSetListener()
			{
			    public void onDateSet(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3)
			    {
			    	
			    	
			    	//"结束日期："
			    		QueryByDateActivity.this.add_end_day.setText(paramInt1 + "-" + (paramInt2 + 1) + "-" + paramInt3);
			    	
			    		
			    		
			    }
			};
		
		   
	}
	
	
	private void fillList()
	{
		    Calendar localCalendar = Calendar.getInstance();
		    int year = localCalendar.get(Calendar.YEAR);
		    int month = localCalendar.get(Calendar.MONTH)+1;
			String str1=new String(year+"-"+month);
			String str;
			List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
			float todaymenoy=0;
			//MyPackage pack=new MyPackage(this);
		//	List<TradeClass> List=pack.getAlltrade();
			List<TradeClass> List=this.getAlltrade();
			for(TradeClass con:List){
				//str = con.gettime();
				//str=str.substring(0, str.lastIndexOf('-'));
				//if(str1.equals(str)){
						//todaymenoy+=con.getMoney();
						Map<String,Object> map=new HashMap<String,Object>();
						map.put("_id", con.getId());
						map.put("money", ""+con.getMoney());
						if(con.getPocketType().equals("日常购物")){
							map.put("icon",R.drawable.richanggouwu);
						}else if(con.getPocketType().equals("交际送礼")){
							map.put("icon",R.drawable.jiaojisongli);
						}else if(con.getPocketType().equals("餐饮开销")){
							map.put("icon",R.drawable.canyingkaixiao);
						}else if(con.getPocketType().equals("购置衣物")){
							map.put("icon",R.drawable.gouziyiwu);
						}else if(con.getPocketType().equals("娱乐开销")){
							map.put("icon",R.drawable.yulekaixiao);
						}else if(con.getPocketType().equals("水电煤气")){
							map.put("icon",R.drawable.shuidianmeiqi);
						}else if(con.getPocketType().equals("网费话费")){
							map.put("icon",R.drawable.wannluohuafei);
						}else if(con.getPocketType().equals("交通出行")){
							map.put("icon",R.drawable.jiaotongchuxing);
						}else if(con.getPocketType().equals("其他花费")){
							map.put("icon",R.drawable.qita);
						}else{
							map.put("icon",R.drawable.qita);
						}				
						map.put("time", con.gettime());
						map.put("type", con.getPocketType());
						list.add(map);
					}
				//}
			localmap = new HashMap<Integer, Boolean>();
			myadapter=new Adapter_LS(QueryByDateActivity.this, list, localmap);
			//textView_all.setText("本月共花费："+(todaymenoy)+"元");
			listView_all.setAdapter(myadapter);
			listView_all.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						// TODO Auto-generated method stub
						
					System.out.println("postion:"+arg2);
					Log.i("Tag", arg2+"");
						
					}
				});
	}
	
	
	
	public List<TradeClass> getAlltrade(){
		Log.i("cusor", this.add_start_day.getText().toString()+"");
		Log.i("cusor", this.add_end_day.getText().toString()+"");
		String a=this.add_start_day.getText().toString();
		String b=this.add_end_day.getText().toString();
		//把查询的记录添加到 list中
		List<TradeClass> TradeList=new ArrayList<TradeClass>();
		dbhelper =new DBHelper(QueryByDateActivity.this);
		db=dbhelper.getWritableDatabase();
		
		if(this.add_start_day.getText().toString()!=null && this.add_end_day.getText().toString()!=null){
		cu=db.rawQuery("select * from tb_outaccount  where addTime between '"+this.add_start_day.getText().toString()+"' and '"+this.add_end_day.getText().toString()+"'", null);
		while(cu.moveToNext()){
			TradeList.add(new ConsumeClass(cu.getInt(cu.getColumnIndex("_id")),cu.getFloat(cu.getColumnIndex("money")),
					cu.getString(cu.getColumnIndex("addTime")),cu.getString(cu.getColumnIndex("mark")),cu.getString(cu.getColumnIndex("pocketType")),
					QueryByDateActivity.this));	
		}
		cu=db.rawQuery("select * from tb_inaccount  where addTime between '"+this.add_start_day.getText().toString()+"' and '"+this.add_end_day.getText().toString()+"'", null);
		while(cu.moveToNext()){
			TradeList.add(new IncomeClass(cu.getInt(cu.getColumnIndex("_id")),cu.getFloat(cu.getColumnIndex("money")),
					cu.getString(cu.getColumnIndex("addTime")),cu.getString(cu.getColumnIndex("mark")),cu.getString(cu.getColumnIndex("pocketType")),
					QueryByDateActivity.this));
		}
		}
		
		for(int i=0;i<cu.getCount();i++){
			Log.i("cusor", TradeList.get(i).getmark()+"");
			Log.i("cusor", TradeList.get(i).getPocketType()+"");
			Log.i("cusor", TradeList.get(i).getId()+"");
			Log.i("cusor", TradeList.get(i).gettime()+"");
			Log.i("cusor", TradeList.get(i).getUsername()+"");
			Log.i("cusor", TradeList.get(i).getMoney()+"");
		}
		return TradeList;
	}

    	
    
	
	
	


public  Dialog onCreateDialog(int paramInt)
	  {
	    Calendar localCalendar = Calendar.getInstance();
	    int i = localCalendar.get(Calendar.YEAR);
	    int j = localCalendar.get(Calendar.MONTH);
	    int k = localCalendar.get(Calendar.DAY_OF_MONTH);
	    
	    switch (paramInt)
	    {
	    default:
	      return null;
	    case 1:
	    	 return new DatePickerDialog(this, this.OnDateSetListener, i, j, k);
	    case 2:
	    	 return new DatePickerDialog(this, this.OnDateSetListener2, i, j, k);
	    }
	   
	  }	

     //监听	DateOnClick
	class DateOnClick implements View.OnClickListener
	{
	  DateOnClick()
	  {
	
	  }
	
	  public void onClick(View paramView)
	  {
		     
			  QueryByDateActivity.this.showDialog(1);	
		      
	  }
     }
	
	 class DateOnClick2 implements View.OnClickListener
		{
		  DateOnClick2()
		  {
		
		  }
		
		  public void onClick(View paramView)
		  {
			     
				  QueryByDateActivity.this.showDialog(2);	
			     
		  }

}
	 
	 
	 
}




