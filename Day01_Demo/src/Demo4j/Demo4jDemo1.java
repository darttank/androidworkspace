package Demo4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Demo4jDemo1 {

	/**
	 * @param args
	 * @throws DocumentException 
	 */
	public static void main(String[] args) throws DocumentException {
		// TODO Auto-generated method stub
		
		SAXReader read=new SAXReader();
		
		Document dom = read.read("book.xml");
		
		Element rootElement = dom.getRootElement();
		
		String author=rootElement.element("Êé").element("×÷Õß").getText();
		
		System.out.println(author);
				

				
	}

	
	
}
