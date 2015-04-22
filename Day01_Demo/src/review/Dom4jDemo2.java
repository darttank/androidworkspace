
package review;

import java.awt.print.Book;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

import javax.sql.rowset.spi.XmlReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import sun.awt.geom.AreaOp.AddOp;

public class Dom4jDemo2 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
            //  Add();
            //  Delete();
           //   Find();
              Update();
	}

	private static void Update() throws DocumentException, Exception, Exception {
		// TODO Auto-generated method stub
		SAXReader saxReader = new  SAXReader();
		Document dom = saxReader.read("book.xml");
		Element rootElement = dom.getRootElement();
		rootElement.element("��").element("�ۼ�").setText("234343.8");
		XMLWriter xmlWriter=new XMLWriter(new FileOutputStream("book.xml"),OutputFormat.createPrettyPrint());
		xmlWriter.write(dom);
		xmlWriter.close();
	
	}

	private static void Find() throws Exception{
		// TODO Auto-generated method stub
		SAXReader saxReader = new SAXReader();
		Document read = saxReader.read("book.xml");
		Element rootElement = read.getRootElement(); 
		
		String nameString=rootElement.element("��").element("����").getText();
		System.out.println(nameString);
		
	}

	private static void Delete()  throws Exception{
		// TODO Auto-generated method stub
		SAXReader saxReader = new SAXReader();
		Document dom = saxReader.read("book.xml");
		Element element = dom.getRootElement().element("��").element("ҳ��");
		element.getParent().remove(element);
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("book.xml"),OutputFormat.createPrettyPrint());
		xmlWriter.write(dom);
		xmlWriter.close();
		
		
		
		
		
	}

	private static void Add()  throws Exception{
		// TODO Auto-generated method stub
		SAXReader saxReader = new SAXReader();
		
		Document dom = saxReader.read("book.xml");
		
		//ѡ��Ҫ��ӵ�Ԫ�صĽڵ�
		Element element = dom.getRootElement().element("��");
		
		// ����һ���ڵ㣬��д�ı�
		Element element2=DocumentHelper.createElement("ҳ��");
		element2.setText("234");
		//���ؽڵ�
		element.add(element2);
		
		XMLWriter xmlWriter=new XMLWriter(new FileOutputStream("book.xml"),OutputFormat.createPrettyPrint());
		
		xmlWriter.write(dom);
		xmlWriter.close();
		
		
	}

}
