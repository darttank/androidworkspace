package com.example.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

//添加自定义类 继承SQLiteOpenHelper
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	public Context mContext;
	
	//创建学生表(学号,姓名,电话,身高) 主键学号
	public static final String createTableStu = "create table Student (" +
			"id integer primary key, " +
			"name text, " +
			"tel text, " +
			"height int)";
	
	public MySQLiteOpenHelper(Context context, String name, CursorFactory factory, 
			int version) {
		super(context, name, factory, version);
		mContext = context;
	}
	
	//创建表
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL(createTableStu);
	}
	//升级表
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL("drop table if exists Student");
		onCreate(arg0);
	}
}