package com.itheima.dom4j;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jDemo1 {
	public static void main(String[] args) throws Exception {
		//1.��ȡ������
		SAXReader reader = new SAXReader();
		//2.����xml��ȡ���������ĵ���dom����
		Document dom = reader.read("book.xml");
		//3.��ȡ���ڵ�
		Element root = dom.getRootElement();
		//4.��ȡ�������д�ӡ
		String bookName = root.element("��").element("����").getText();
		System.out.println(bookName);
	}
}
