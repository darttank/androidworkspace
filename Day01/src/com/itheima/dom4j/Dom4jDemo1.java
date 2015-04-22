package com.itheima.dom4j;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jDemo1 {
	public static void main(String[] args) throws Exception {
		//1.获取解析器
		SAXReader reader = new SAXReader();
		//2.解析xml获取代表整个文档的dom对象
		Document dom = reader.read("book.xml");
		//3.获取根节点
		Element root = dom.getRootElement();
		//4.获取书名进行打印
		String bookName = root.element("书").element("书名").getText();
		System.out.println(bookName);
	}
}
