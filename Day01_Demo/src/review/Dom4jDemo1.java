package review;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jDemo1  {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SAXReader saxReader = new SAXReader();
		
		Document dom = saxReader.read("book.xml");
		
		Element rootElement = dom.getRootElement();
		
		String text = rootElement.element(" È").element(" È√˚").getText();
		
		System.out.println(text);
		
		

	}

}
