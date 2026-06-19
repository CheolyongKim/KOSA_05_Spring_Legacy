package DI_07_Spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
//		ProtocolHandler handler = new ProtocolHandler();
//		// Java API 제공 Collection 사용
//		List<MyFilter> list = new ArrayList<MyFilter>();
//		list.add(new EncFilter());
//		list.add(new ZipFilter());
//		list.add(new HeaderFilter());
//		handler.setFilters(list);	// Injection
//		
//		System.out.println(handler.filter_Length());
		
		// Spring으로 만들기 -> new가 있는 것들 모두 bean으로 올려야
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_07_Spring/DI_07.xml");
		ProtocolHandler protocolHandler = context.getBean("protocolHandler", ProtocolHandler.class);
		System.out.println(protocolHandler.filter_Length());
	}

}
