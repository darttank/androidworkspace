package review;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class SAXDemo {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// 生成SAX解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//用工厂生成SAX解析器
		SAXParser newSAXParser = factory.newSAXParser();
		//用SAX解析器生成xml读取器
		XMLReader xmlReader = newSAXParser.getXMLReader();
		//把SAX读取器设置监听
		xmlReader.setContentHandler(new ContentHandler2());
		//转换xml文件
		xmlReader.parse("book.xml");
		
	}

}

class ContentHandler2 extends DefaultHandler {
	String elename=null;
	int count=0;
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		this.elename=qName;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		if("书名".equals(elename))
		System.out.println(new String(ch,start,length));
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}


	
	
	
}
