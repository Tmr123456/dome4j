package src.test.java.dom4j;



import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class DomTest {
	@Test
	// 读取 xml 文件 内容
	public void testReadFile(){
		//1、创建对象SAXreader();
		SAXReader reader=new SAXReader();
		// 2. read xml 文件
		Document doc=null;
		try {
			doc=reader.read("src/main/resources/aaa.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 3. 取得 root 节点
		Element root=doc.getRootElement();
		System.out.println(root);
		// 4. 取得 具体的元素
		// teachers -> teacher -> name 
		Element name=root.element("teacher").element("name");
		System.out.println(name.getText());
		// list 的 遍历
		List<Element> list=root.elements();
		for (Element e : list) {
			System.out.println(e.element("name").getText());
			System.out.println(e.element("sex").getText());
		}
		// 取得  attribute 
		Attribute attr= root.element("teacher").attribute("id");
		System.out.println(attr.getText());
	}
	@Test
	public void testWriteXmlFile() {
		// 1. 创建一个 document 对象
		Document doc=DocumentHelper.createDocument();
		// 2. 添加元素 root
		Element root=doc.addElement("beans");
		// 3 . 添加其他 元素
		root.addElement("bean").addAttribute("id", "userDao").
		addAttribute("class", "com.hc.dao.UserDaoImpl");
		root.addElement("bean").addAttribute("id", "itensDao").
		addAttribute("class", "com.hc.dao.ItensDaoIml");
		OutputFormat format=OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		// 4. 创建一个 XMLWriter 对象
		try {
			XMLWriter writer=new XMLWriter(new FileWriter("src/main/resources/applicationContext.xml"));
			// 5.  回写文件 （document 写入到 XMLWriter
			writer.write(doc);
			// 6.  关闭 xmlWriter
			writer.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
