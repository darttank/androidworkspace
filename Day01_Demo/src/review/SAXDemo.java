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
		// ����SAX��������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//�ù�������SAX������
		SAXParser newSAXParser = factory.newSAXParser();
		//��SAX����������xml��ȡ��
		XMLReader xmlReader = newSAXParser.getXMLReader();
		//��SAX��ȡ�����ü���
		xmlReader.setContentHandler(new ContentHandler2());
		//ת��xml�ļ�
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
		if("����".equals(elename))
		System.out.println(new String(ch,start,length));
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}


	
	
	
}
