package com.tianfan.moneylife;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tianfan.bean.ConsumeClass;
import com.tianfan.bean.IncomeClass;
import com.tianfan.bean.TradeClass;
//import db.DBHelper;
import com.tianfan.db.*;

public class MyPackage {
	float income_sum;
	float consume_sum;
	private DBHelper dbhelper;
	private SQLiteDatabase db;
	private Context context;
	//public 
	public MyPackage(Context context){
		this.context=context;
		dbhelper=new DBHelper(context);
	}
	public float getincome(){
		return income_sum;
	} 
	
	public float getconsume(){
		return consume_sum;
	}
	public List<TradeClass> getAlltrade(){
		//把查询的记录添加到 list中
		List<TradeClass> TradeList=new ArrayList<TradeClass>();
		db=dbhelper.getReadableDatabase();
		Cursor cu=db.rawQuery("select * from tb_outaccount", null);
		while(cu.moveToNext()){
			TradeList.add(new ConsumeClass(cu.getInt(cu.getColumnIndex("_id")),cu.getFloat(cu.getColumnIndex("money")),
					cu.getString(cu.getColumnIndex("addTime")),cu.getString(cu.getColumnIndex("mark")),cu.getString(cu.getColumnIndex("pocketType")),
					context));	
		}
		cu=db.rawQuery("select * from tb_inaccount", null);
		while(cu.moveToNext()){
			TradeList.add(new IncomeClass(cu.getInt(cu.getColumnIndex("_id")),cu.getFloat(cu.getColumnIndex("money")),
					cu.getString(cu.getColumnIndex("addTime")),cu.getString(cu.getColumnIndex("mark")),cu.getString(cu.getColumnIndex("pocketType")),
					context));
		}
		return TradeList;
	}
	
	
	
}
