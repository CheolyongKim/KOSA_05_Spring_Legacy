package DI_03_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
//		MessageBean messageBean = new MessageBean_kr();
//		MessageBean messageBean = new MessageBean_en();
//		messageBean.sayHello("hong");
		
		// 1. Spring Container 만들기
		// 2. Container 내에 필요한 객체를 생성하고 주입하기 by xml
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_03_Spring/DI_03.xml");
		
		// 3. 그 Container 내에서 필요한 객체를 얻어내기 by getBean
		MessageBean messageBean = context.getBean("messageBean", MessageBean.class);
		messageBean.sayHello("hong");
	}
}