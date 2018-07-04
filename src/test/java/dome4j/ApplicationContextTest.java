package dome4j;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApplicationContextTest {

	@Test
	public void testGetBean() {
		ApplicationContext ac=new ClassPathXmlApplicatio("applicationContext.xml");
	    ac.getBean("person");
	}

}
