package dome4j;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class ClassPathXmlApplicatio implements ApplicationContext{
	Map<String, Object> list=new HashMap<String,Object>();
	public String pathName;
	
	public ClassPathXmlApplicatio (String pathName) {
		this.pathName=pathName;
		SAXReader reader=new SAXReader();
		try {
			Document doc=reader.read(this.getClass().getResourceAsStream("/"+pathName));
			List<Node> nodes=doc.selectNodes("//beans");
			for (Node n : nodes) {
				String id=n.valueOf("@id");
				String className = n.valueOf("@class");
				Class<?> clazz=Class.forName(className);
				list.put(id, clazz.newInstance());
			}
			System.out.println(list);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void getBean() {
		// TODO Auto-generated method stub
		
	}

}
