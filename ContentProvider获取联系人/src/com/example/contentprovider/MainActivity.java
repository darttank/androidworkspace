package com.example.contentprovider;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getContacts();
	}

	private void getContacts() {
		// 得到ContentResolver对象   
		ContentResolver cr = this.getContentResolver();     
		// 取得电话本中开始一项的光标,主要就是查询"contacts"表
		Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);   
		while (cursor.moveToNext())   
		{   
			StringBuilder sbLog = new StringBuilder();
			
		    // 取得联系人名字 (显示出来的名字)，实际内容在 ContactsContract.Contacts中
		   int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);   
		   String name = cursor.getString(nameIndex);
		   sbLog.append("name=" + name + ";");
		    
		    // 取得联系人ID
		    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));   
		    
		    // 根据联系人ID查询对应的电话号码
		    Cursor phoneNumbers = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "  
		            + contactId, null, null);   		  
		    // 取得电话号码(可能存在多个号码)   
		    while (phoneNumbers.moveToNext())   
		    {
		        String strPhoneNumber = phoneNumbers.getString(phoneNumbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));   
		        sbLog.append("Phone=" + strPhoneNumber + ";");
		    }   
		    phoneNumbers.close(); 
		    
		    // 根据联系人ID查询对应的email
		    Cursor emails = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = "  
		            + contactId, null, null);   		  
		    // 取得email(可能存在多个email)   
		    while (emails.moveToNext())   
		    {
		        String strEmail = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));   
		        sbLog.append("Email=" + strEmail + ";");
		    }   
		    emails.close(); 
		    
		    Log.i("TAG", sbLog.toString());
		}
		cursor.close();		    
	}

}
