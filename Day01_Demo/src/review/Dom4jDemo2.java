
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
		rootElement.element("书").element("售价").setText("234343.8");
		XMLWriter xmlWriter=new XMLWriter(new FileOutputStream("book.xml"),OutputFormat.createPrettyPrint());
		xmlWriter.write(dom);
		xmlWriter.close();
	
	}

	private static void Find() throws Exception{
		// TODO Auto-generated method stub
		SAXReader saxReader = new SAXReader();
		Document read = saxReader.read("book.xml");
		Element rootElement = read.getRootElement(); 
		
		String nameString=rootElement.element("书").element("书名").getText();
		System.out.println(nameString);
		
	}

	private static void Delete()  throws Exception{
		// TODO Auto-generated method stub
		SAXReader saxReader = new SAXReader();
		Document dom = saxReader.read("book.xml");
		Element element = dom.getRootElement().element("书").element("页数");
		element.getParent().remove(element);
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("book.xml"),OutputFormat.createPrettyPrint());
		xmlWriter.write(dom);
		xmlWriter.close();
		
		
		
		
		
	}

	private static void Add()  throws Exception{
		// TODO Auto-generated method stub
		SAXReader saxReader = new SAXReader();
		
		Document dom = saxReader.read("book.xml");
		
		//选择要添加的元素的节点
		Element element = dom.getRootElement().element("书");
		
		// 创建一个节点，并写文本
		Element element2=DocumentHelper.createElement("页数");
		element2.setText("234");
		//挂载节点
		element.add(element2);
		
		XMLWriter xmlWriter=new XMLWriter(new FileOutputStream("book.xml"),OutputFormat.createPrettyPrint());
		
		xmlWriter.write(dom);
		xmlWriter.close();
		
		
	}

}
