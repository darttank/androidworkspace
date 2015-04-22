package com.example.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

//����Զ����� �̳�SQLiteOpenHelper
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	public Context mContext;
	
	//����ѧ����(ѧ��,����,�绰,���) ����ѧ��
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
	
	//������
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL(createTableStu);
	}
	//������
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL("drop table if exists Student");
		onCreate(arg0);
	}
}