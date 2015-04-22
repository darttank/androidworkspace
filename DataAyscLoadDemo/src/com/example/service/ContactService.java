package com.example.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

import com.example.domain.Contact;

public class ContactService {

	public static List<Contact> GetContacts() throws Exception, IOException {
		
		String path="http://192.168.1.107:8080/test/list.xml";
		HttpURLConnection conn=	(HttpURLConnection)new URL(path).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if(conn.getResponseCode()==200){
			
			return parseXML(conn.getInputStream());
			
		}
	
		return null;
	}

	private static List<Contact> parseXML(InputStream xml) throws Exception {
		Contact contact = null;
		List<Contact> contacts=new ArrayList<Contact>();
		// TODO Auto-generated method stub
		XmlPullParser PullParser = Xml.newPullParser();
		PullParser.setInput(xml, "UTF-8");
		
		int event= PullParser.getEventType();
	while(event!=XmlPullParser.END_DOCUMENT){
		switch (event) {
		case XmlPullParser.START_TAG:
			if("contact".equals(PullParser.getName())){
				 contact.id= Integer.parseInt(PullParser.getAttributeValue(0));
				 
			}else if("name".equals(PullParser.getName())){
				contact.name=PullParser.nextText();
			}else if("image".equals(PullParser.getName())){
				contact.image=PullParser.getAttributeName(0);
				
			}
		case XmlPullParser.END_TAG:
			if("contact".endsWith(PullParser.getName())){
				contacts.add(contact);
				contact=null;
			}
			
			break;

		default:
			break;
		}	
		event = PullParser.next();
	
		}
		

		return contacts;
	}

}
