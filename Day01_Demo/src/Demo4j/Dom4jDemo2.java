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
		//1.��ý�����
		SAXReader reader = new SAXReader();
		
		//2.���dom�ĵ�����
		Document dom = reader.read("book.xml");
		
		//3.���dom�ĵ��ĸ��ڵ�
		Element rootElement = dom.getRootElement();

		//4.���dom�ĵ��ĸ��ڵ��µĽڵ�
		Element element = rootElement.element("��");
		
		/* element.addAttribute("������", "������ѧ������");
		  
		  String addAttribute =element.attributeValue("������");
		  System.out.println(addAttribute);
		 */

	/*	Attribute attribute = element.attribute("������");
		//ɾ��һ�����ԣ���֪һ�����ԣ������Ի�ȡ�Լ��ĸ��ڵ㣬Ȼ���ø��ڵ���ɾ������Ϊ�Լ�����ɾ���Լ�
		attribute.getParent().remove(attribute);*/

		//���޸ĺõ�д�뵽�ĵ���
		XMLWriter writer = new XMLWriter(new FileOutputStream("book.xml"),
				OutputFormat.createPrettyPrint());
		/*
		OutputFormat opt=OutputFormat.createPrettyPrint();     //�������
       */ 
		writer.write(dom);
		writer.close();
	}
	
	//���ӽڵ�
	@Test
	public void Add() throws Exception{
		    SAXReader reader=new SAXReader();
		    Document dom = reader.read("book.xml");
		    Element rootElement = dom.getRootElement();
		    
		           
		          Element elementPrice = DocumentHelper.createElement("�ؼ�");
		          elementPrice.setText("29.8");
		          
		         Element element = rootElement.element("��");
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
		
		Element elementAuthor= rootElement.element("��").element("����");
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
		
		rootElement.element("��").element("�ؼ�").setText("3.43");
		
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
	   System.out.println(element.element("����").getText());
		
		
		
		
	}
	
}
