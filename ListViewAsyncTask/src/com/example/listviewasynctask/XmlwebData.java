package com.example.listviewasynctask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class XmlwebData 
{
    private static ArrayList<Person> persons=null; 
    public static ArrayList<Person> getData(final String path)
    {
                try 
                {
                    URL url=new URL(path);
                    Person person=null;
                    HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    if(conn.getResponseCode()==200)
                    {
                        InputStream inputstream=conn.getInputStream(); 
                        XmlPullParser xml=Xml.newPullParser();
                        xml.setInput(inputstream, "UTF-8");
                        int event=xml.getEventType();
                        
                        while(event!=XmlPullParser.END_DOCUMENT)
                        {
                            switch (event) 
                            {
                            //��ʼ�����ĵ�
                            case XmlPullParser.START_DOCUMENT:
                                persons=new ArrayList<Person>();
                                break;
                            case XmlPullParser.START_TAG:
                                
                                String value=xml.getName();
                                if(value.equals("person"))
                                {//person����ĳ�ʼ�������������ʼ����Ȼ���ܳ���Ϊnull������
                                    person=new Person();
                                    //��ȡ����ֵ
                                    person.setId(new Integer(xml.getAttributeValue(0)));
                                }
                                else if(value.equals("name"))
                                {
                                    person.setName(xml.nextText());
                                }
                                else if(value.equals("sex"))
                                {
                                    person.setSex(xml.nextText());
                                }
                                else if(value.equals("age"))
                                {
                                    person.setAge(new Integer(xml.nextText()));
                                }
                                else if(value.equals("path"))
                                {
                                    person.setPath(xml.nextText());
                                }
                                break;
                            case XmlPullParser.END_TAG:
                                if(xml.getName().equals("person"))
                                {
                                    persons.add(person);
                                    System.out.println(person.getName());;
                                    person=null;
                                }
                                break;
                            }
                            //������һ������
                            event=xml.next();
                        }
                        return persons;
                    }
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                } 
            
        
        return null;
    
    }

}
