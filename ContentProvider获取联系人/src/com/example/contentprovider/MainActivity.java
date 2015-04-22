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
		// �õ�ContentResolver����   
		ContentResolver cr = this.getContentResolver();     
		// ȡ�õ绰���п�ʼһ��Ĺ��,��Ҫ���ǲ�ѯ"contacts"��
		Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);   
		while (cursor.moveToNext())   
		{   
			StringBuilder sbLog = new StringBuilder();
			
		    // ȡ����ϵ������ (��ʾ����������)��ʵ�������� ContactsContract.Contacts��
		   int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);   
		   String name = cursor.getString(nameIndex);
		   sbLog.append("name=" + name + ";");
		    
		    // ȡ����ϵ��ID
		    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));   
		    
		    // ������ϵ��ID��ѯ��Ӧ�ĵ绰����
		    Cursor phoneNumbers = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "  
		            + contactId, null, null);   		  
		    // ȡ�õ绰����(���ܴ��ڶ������)   
		    while (phoneNumbers.moveToNext())   
		    {
		        String strPhoneNumber = phoneNumbers.getString(phoneNumbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));   
		        sbLog.append("Phone=" + strPhoneNumber + ";");
		    }   
		    phoneNumbers.close(); 
		    
		    // ������ϵ��ID��ѯ��Ӧ��email
		    Cursor emails = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = "  
		            + contactId, null, null);   		  
		    // ȡ��email(���ܴ��ڶ��email)   
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
