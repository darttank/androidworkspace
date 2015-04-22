package com.itheima.sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;


public class SaxDemo1 {
	public static void main(String[] args) throws Exception {
		//1.获取解析器工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//2.通过工厂获取sax解析器
		SAXParser parser = factory.newSAXParser();
		//3.获取读取器
		XMLReader reader = parser.getXMLReader();
		//4.注册事件处理器
		reader.setContentHandler(new MyContentHandler2() );
		//5.解析xml
		reader.parse("book.xml");
		
		
	}
}

//适配器设计模式
class MyContentHandler2 extends DefaultHandler{
	private String eleName = null;
	private int count = 0;
	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		this.eleName = name;
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if("书名".equals(eleName) && ++count==2){
			System.out.println(new String(ch,start,length));
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		eleName = null;
	}
	
}




class MyContentHandler implements ContentHandler{

	public void startDocument() throws SAXException {
		System.out.println("文档解析开始了.......");
	}
	
	public void startElement(String uri, String localName, String name,
			Attributes atts) throws SAXException {
		System.out.println("发现了开始标签,"+name);
	}
	
	public void characters(char[] ch, int start, int length)
		throws SAXException {
		System.out.println(new String(ch,start,length));
	}
	
	public void endElement(String uri, String localName, String name)
		throws SAXException {
		System.out.println("发现结束标签,"+name);
	}

	
	public void endDocument() throws SAXException {
		System.out.println("文档解析结束了.......");
	}
	
	
	
	
	
	
	
	
	
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}

	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	

	

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}
	
}
