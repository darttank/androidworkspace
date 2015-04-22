package com.tianfan.bean;

import java.util.Date;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
//import db.DBHelper;
import com.tianfan.db.*;

public abstract class TradeClass {
	private int _id;
	private float money;
	private String time;
	private String packageType;
	private String mark;
	private DBHelper dbhelper;
	private SQLiteDatabase db;
	private String tablename;
	private String username,password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public TradeClass(int id,float money,String time2,String mark,String packageType,Context context){
		this._id=id;
		this.mark=mark;
		this.money=money;
		this.packageType=packageType;
		this.time=time2;
		dbhelper=new DBHelper(context);
		
	}
	//_id INTEGER PRIMARY KEY,pocketType varchar(20),addTime date,money float,mark TEXT
	public void trade_add(){
	    db=dbhelper.getWritableDatabase();
	    try
	    {
	      ContentValues localContentValues = new ContentValues();
	      //localContentValues.put("_id", GetMaxid());
	      localContentValues.put("pocketType", this.packageType);
	      localContentValues.put("addTime", this.time);
	      localContentValues.put("money", this.money);
	      localContentValues.put("mark", this.mark);
	      db.insert(tablename, null, localContentValues);
	      db.close();
	      return;
	    }
	    catch (Exception localException)
	    {
	    	Log.v("id", "add consume error");
	    }
	}
	public void trade_modify(){
		
	}
	public int trade_delect(int id){
	    db=dbhelper.getWritableDatabase();
	    try
	    {
	      db.delete(tablename, "_id="+id, null);
	      return 1;
	    }
	    catch (Exception localException)
	    {
	      return 0;
	    }
	}

	
	private int GetMaxid(){
		db=dbhelper.getWritableDatabase();
		try{
			Cursor cu=db.rawQuery("select max(_id) from "+this.tablename, null);
				while(cu.moveToLast()){
					return cu.getInt(0);
				}
			cu.close();
			db.close();
			}catch(Exception localException){			
		}
		return 0;
	}
	
	public float getMoney()
	{
	    return this.money;
	}

	public String gettime()
	{
	    return this.time;
	}

	public int getId()
	{
	    return this._id;
	}

	public String getPocketType()
	{
	    return this.packageType;
	}

	public void setMoney(float paramFloat)
	{
	    this.money = paramFloat;
	}

	public void settime(String paramDate)
	{
	    this.time = paramDate;
	}

	public void setPocketId(int paramInt)
	{
	    this._id = paramInt;
	}

	public void setPocketType(String paramString)
	{
	   this.packageType = paramString;
	}
	public String getmark()
	{
	    return this.mark;
	}

	public void setmark(String mark)
	{
	    this.mark = mark;
	}
	public void settablename(String tablename)
	{
	    this.tablename = tablename;
	}
	
}

 