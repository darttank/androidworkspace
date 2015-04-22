package sax_demo;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import sun.nio.cs.ext.TIS_620;

public class SaxDemo {

	/**
	 * @param args
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		
		SAXParserFactory newInstance = SAXParserFactory.newInstance();
		SAXParser newSAXParser = newInstance.newSAXParser();
		
		XMLReader xmlReader = newSAXParser.getXMLReader();
		
		xmlReader.setContentHandler(new ContentHandler2());
		
		xmlReader.parse("book.xml");
		

	}

}

class ContentHandler2 extends DefaultHandler{
	
	String  elename=null;
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
		if("зїеп".equals(elename) && ++count==2){
			System.out.println(new String(ch, start, length));
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		
		this.elename=null;
	}
	
}
