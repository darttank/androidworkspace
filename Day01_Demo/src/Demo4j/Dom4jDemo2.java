package Demo4j;

import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import sun.awt.geom.AreaOp.AddOp;

public class Dom4jDemo2 {
	@Test
	public void attr() throws Exception {
		//1.获得解析器
		SAXReader reader = new SAXReader();
		
		//2.获得dom文档对象
		Document dom = reader.read("book.xml");
		
		//3.获得dom文档的根节点
		Element rootElement = dom.getRootElement();

		//4.获得dom文档的根节点下的节点
		Element element = rootElement.element("书");
		
		/* element.addAttribute("出版社", "复旦大学出版社");
		  
		  String addAttribute =element.attributeValue("出版社");
		  System.out.println(addAttribute);
		 */

	/*	Attribute attribute = element.attribute("出版社");
		//删除一个属性，已知一个属性，用属性获取自己的父节点，然后用父节点来删除。因为自己不能删除自己
		attribute.getParent().remove(attribute);*/

		//把修改好的写入到文档中
		XMLWriter writer = new XMLWriter(new FileOutputStream("book.xml"),
				OutputFormat.createPrettyPrint());
		/*
		OutputFormat opt=OutputFormat.createPrettyPrint();     //输出数据
       */ 
		writer.write(dom);
		writer.close();
	}
	
	//增加节点
	@Test
	public void Add() throws Exception{
		    SAXReader reader=new SAXReader();
		    Document dom = reader.read("book.xml");
		    Element rootElement = dom.getRootElement();
		    
		           
		          Element elementPrice = DocumentHelper.createElement("特价");
		          elementPrice.setText("29.8");
		          
		         Element element = rootElement.element("书");
		         element.add(elementPrice);
		          
		          XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("book.xml"), OutputFormat.createPrettyPrint());
		          xmlWriter.write(dom);
		          xmlWriter.close();
		          
	}
	
	@Test
	public void delete() throws Exception{
		
		SAXReader saxReader = new SAXReader();
		Document dom = saxReader.read("book.xml");
		Element rootElement = dom.getRootElement();
		
		Element elementAuthor= rootElement.element("书").element("作者");
		elementAuthor.getParent().remove(elementAuthor);
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("book.xml"),OutputFormat.createPrettyPrint());
		
		xmlWriter.write(dom);
		xmlWriter.close();
		
	}
	
	@Test
	public void Update() throws Exception{
		
		SAXReader saxReader = new SAXReader();
		Document dom = saxReader.read("book.xml");
		Element rootElement = dom.getRootElement();
		
		rootElement.element("书").element("特价").setText("3.43");
		
	   XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("book.xml"), OutputFormat.createPrettyPrint());
	   xmlWriter.write(dom);
	   xmlWriter.close();
		
		
	}
	
	@Test
	public void find () throws Exception{
		SAXReader saxReader = new SAXReader();
		Document dom = saxReader.read("book.xml");
	   Element rootElement = dom.getRootElement();
	   
	   List <Element> list = rootElement.elements();
	   Element element = list.get(0);
	   System.out.println(element.element("书名").getText());
		
		
		
		
	}
	
}
