package com.example.sqlitedemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.R.integer;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
	private MySQLiteOpenHelper sqlHelper;
	private ListView listview;
	private EditText edit_id;
	private EditText edit_name;
	private EditText edit_tel;
	private EditText edit_height;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		sqlHelper = new MySQLiteOpenHelper(this, "StuDatabase.db", null, 1);
		sqlHelper.getWritableDatabase();

		Button insertBtn = (Button) findViewById(R.id.insert_btn);
		edit_id = (EditText) findViewById(R.id.edit_id);
		edit_name = (EditText) findViewById(R.id.edit_name);
		edit_tel = (EditText) findViewById(R.id.edit_tel);
		edit_height = (EditText) findViewById(R.id.edit_height);

		// 插入数据
		insertBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String id = edit_id.getText().toString();
				String name = edit_name.getText().toString();
				String tel = edit_tel.getText().toString();
				String height = edit_height.getText().toString();

				// 用户输入控制
				if (isNotNumeric(id)) {
					Toast.makeText(MainActivity.this, "学号必须是数字",
							Toast.LENGTH_SHORT).show();

				} else if (isNumeric(name)) {

					Toast.makeText(MainActivity.this, "姓名必须是字母和中文",
							Toast.LENGTH_SHORT).show();
				} else if (isNotNumeric(tel)) {
					Toast.makeText(MainActivity.this, "电话号码必须是数字",
							Toast.LENGTH_SHORT).show();
				} else if (isNotNumeric(height)) {
					Toast.makeText(MainActivity.this, "身高必须是数字",
							Toast.LENGTH_SHORT).show();
				//判断学生的id是否存在
				} else if (isHas(id) != null) {
					Toast.makeText(MainActivity.this, "此学号已经存在",
							Toast.LENGTH_SHORT).show();
				} else {
					SQLiteDatabase db = sqlHelper.getWritableDatabase();
					ContentValues values = new ContentValues();
					values.put("id", id);
					values.put("name", name);
					values.put("tel", tel);
					values.put("height", height);
					db.insert("Student", null, values);
					Toast.makeText(MainActivity.this, "数据插入成功",
							Toast.LENGTH_SHORT).show();
					edit_id.setText("");
					edit_name.setText("");
					edit_tel.setText("");
					edit_height.setText("");
					edit_id.setEnabled(true);
					// 查询所有的数据
					search();
				}

			}
		});

		// 查询数据
		listview = (ListView) findViewById(R.id.listview1);

		Button search_btn = (Button) findViewById(R.id.search_btn);
		search_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 查询所有的数据
				search();
			}
		});

		// 获取每个item的编号
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				SQLiteDatabase db = sqlHelper.getWritableDatabase();
				// 游标查询每条数据
				Cursor cursor = db.query("Student", null, null, null, null,
						null, null);
				// 定义list存储数据
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				// 适配器SimpleAdapter数据绑定
				// 错误:构造函数SimpleAdapter未定义 需把this修改为MainActivity.this
				SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,
						list, R.layout.stu_item, new String[] { "id", "name",
								"tel", "height" }, new int[] { R.id.stu_id,
								R.id.stu_name, R.id.stu_tel, R.id.stu_height });
				// 读取数据 游标移动到下一行
				while (cursor.moveToNext()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", cursor.getString(cursor.getColumnIndex("id")));
					map.put("name",
							cursor.getString(cursor.getColumnIndex("name")));
					map.put("tel",
							cursor.getString(cursor.getColumnIndex("tel")));
					map.put("height",
							cursor.getString(cursor.getColumnIndex("height")));
					list.add(map);
				}
				listview.setAdapter(adapter);
				edit_id.setText(list.get(arg2).get("id").toString() + "");
				edit_name.setText(list.get(arg2).get("name").toString() + "");
				edit_tel.setText(list.get(arg2).get("tel").toString() + "");
				edit_height.setText(list.get(arg2).get("height").toString()
						+ "");
				if (edit_id.getText().toString() != null) {
					edit_id.setEnabled(false);
				}
			}
		});

		// 删除数据
		Button delete_btn = (Button) findViewById(R.id.delete_btn);
		delete_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (edit_id.getText() != null) {
					String id = edit_id.getText().toString();

					SQLiteDatabase db = sqlHelper.getWritableDatabase();

					db.execSQL("delete from Student where id=?",
							new Object[] { id });

				}
				edit_id.setText("");
				edit_name.setText("");
				edit_tel.setText("");
				edit_height.setText("");
				edit_id.setEnabled(true);
				Toast.makeText(MainActivity.this, "删除完成", Toast.LENGTH_SHORT)
						.show();
				// 查询所有的数据
				search();
			}
		});

		// 修改数据
		Button update_btn = (Button) findViewById(R.id.update_btn);
		update_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 用户输入控制
				if (isNotNumeric(edit_id.getText().toString())) {
					Toast.makeText(MainActivity.this, "学号必须是数字",
							Toast.LENGTH_SHORT).show();
				} else if (isNumeric(edit_name.getText().toString())) {

					Toast.makeText(MainActivity.this, "姓名必须是字母和中文",
							Toast.LENGTH_SHORT).show();
				} else if (isNotNumeric(edit_tel.getText().toString())) {
					Toast.makeText(MainActivity.this, "电话号码必须是数字",
							Toast.LENGTH_SHORT).show();
				} else if (isNotNumeric(edit_height.getText().toString())) {
					Toast.makeText(MainActivity.this, "身高必须是数字",
							Toast.LENGTH_SHORT).show();
				} else {
					SQLiteDatabase db = sqlHelper.getWritableDatabase();
					String id = edit_id.getText().toString();
					String name = edit_name.getText().toString();
					String tel = edit_tel.getText().toString();
					String height = edit_height.getText().toString();
					db.execSQL(
							"update Student set id=?,name=?,tel=?,height=? where id=?",
							new Object[] { id, name, tel, height, id });

					edit_id.setText("");
					edit_name.setText("");
					edit_tel.setText("");
					edit_height.setText("");
					edit_id.setEnabled(true);
					Toast.makeText(MainActivity.this, "修改完成",
							Toast.LENGTH_SHORT).show();
					// 查询所有的数据
					search();
				}

			}

		});

	}

	// 查询所有数据
	public void search() {

		try {
			SQLiteDatabase db = sqlHelper.getWritableDatabase();
			// 游标查询每条数据
			Cursor cursor = db.query("Student", null, null, null, null, null,
					null);
			// 定义list存储数据
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			// 适配器SimpleAdapter数据绑定
			// 错误:构造函数SimpleAdapter未定义 需把this修改为MainActivity.this
			SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, list,
					R.layout.stu_item, new String[] { "id", "name", "tel",
							"height" }, new int[] { R.id.stu_id, R.id.stu_name,
							R.id.stu_tel, R.id.stu_height });
			// 读取数据 游标移动到下一行
			while (cursor.moveToNext()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", cursor.getString(cursor.getColumnIndex("id")));
				map.put("name", cursor.getString(cursor.getColumnIndex("name")));
				map.put("tel", cursor.getString(cursor.getColumnIndex("tel")));
				map.put("height",
						cursor.getString(cursor.getColumnIndex("height")));
				list.add(map);
			}
			listview.setAdapter(adapter);
			edit_id.setText("");
			edit_name.setText("");
			edit_tel.setText("");
			edit_height.setText("");
			edit_id.setEnabled(true);
		} catch (Exception e) {
			Log.i("exception", e.toString());
		}

	}

	// 判断字符串不是为数字
	public boolean isNotNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return true;
		}
		return false;
	}

	// 判断字符串是数字
	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
    //判断学生id是否存在
	public String isHas(String id) {
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		// 游标查询每条数据
		Cursor cursor = db.rawQuery("select * from Student where id=?",
				new String[] { id });
		// 读取数据 游标移动到下一行
		while (cursor.moveToNext()) {
			String hasId = cursor.getString(cursor.getColumnIndex("id"));
			if (hasId != null) {
				return hasId;
			}

		}
		return null;

	}

}
