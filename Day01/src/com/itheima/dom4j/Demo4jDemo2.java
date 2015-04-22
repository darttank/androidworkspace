package com.itheima.dom4j;

import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class Demo4jDemo2 {
	@Test
	public void attr() throws Exception{
		SAXReader reader = new SAXReader();
		Document dom = reader.read("book.xml");
		Element root = dom.getRootElement();
		
		Element bookEle = root.element("书");
		//bookEle.addAttribute("出版社", "传智出版社");
//		String str = bookEle.attributeValue("出版社");
//		System.out.println(str);
		Attribute attr = bookEle.attribute("出版社");
		attr.getParent().remove(attr);
		
		XMLWriter writer = new XMLWriter(new FileOutputStream("book.xml"),OutputFormat.createPrettyPrint());
		writer.write(dom);
		writer.close();
	}
	
	@Test
	public void del() throws Exception{
		SAXReader reader = new SAXReader();
		Document dom = reader.read("book.xml");
		Element root = dom.getRootElement();
		
		Element price2Ele = root.element("书").element("特价");
		price2Ele.getParent().remove(price2Ele);

		XMLWriter writer = new XMLWriter(new FileOutputStream("book.xml"),OutputFormat.createPrettyPrint());
		writer.write(dom);
		writer.close();
	}
	
	@Test
	public void update()throws Exception{
		SAXReader reader = new SAXReader();
		Document dom = reader.read("book.xml");
		Element root = dom.getRootElement();
		
		root.element("书").element("特价").setText("4.0元");
		
		XMLWriter writer = new XMLWriter(new FileOutputStream("book.xml"),OutputFormat.createPrettyPrint());
		writer.write(dom);
		writer.close();
	}
	
	@Test
	public void add()throws Exception{
		SAXReader reader = new SAXReader();
		Document dom = reader.read("book.xml");
		Element root = dom.getRootElement();
		//凭空创建<特价>节点,设置标签体
		Element price2Ele = DocumentHelper.createElement("特价");
		price2Ele.setText("40.0元");
		//获取父标签<书>将特价节点挂载上去
		Element bookEle = root.element("书");
		bookEle.add(price2Ele);
		
		//将内存中的dom树会写到xml文件中,从而使xml中的数据进行更新
//		FileWriter writer = new FileWriter("book.xml");
//		dom.write(writer);
//		writer.flush();
//		writer.close();
		XMLWriter writer = new XMLWriter(new FileOutputStream("book.xml"),OutputFormat.createPrettyPrint());
		writer.write(dom);
		writer.close();
	}
	
	@Test
	public void find() throws Exception{
		SAXReader reader = new SAXReader();
		Document dom = reader.read("book.xml");
		Element root = dom.getRootElement();
		
		List<Element> list =  root.elements();
		Element book2Ele = list.get(1);
		System.out.println(book2Ele.element("书名").getText());
		
	}
}
