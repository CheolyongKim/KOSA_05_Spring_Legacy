package DI_05_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		MyBean MyBean = new MyBean();
		MyBean MyBean2 = new MyBean();
		MyBean MyBean3 = new MyBean();
		System.out.println(MyBean + " / " + MyBean2 + " / " + MyBean3);
		
		Singleton singleton = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		Singleton singleton3 = Singleton.getInstance();
		System.out.println(singleton + " / " + singleton2 + " / " + singleton3);
		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_05_Spring/DI_05.xml");
		
		MyBean m = context.getBean("myBean", MyBean.class);
		MyBean m2 = context.getBean("myBean", MyBean.class);
		MyBean m3 = context.getBean("myBean", MyBean.class);
		System.out.println(m+" / "+m2+" / "+m3);
		
		Singleton s = context.getBean("single", Singleton.class);
		Singleton s2 = context.getBean("single", Singleton.class);
		Singleton s3 = context.getBean("single", Singleton.class);
		System.out.println(s+" / "+s2+" / "+s3);
	}

}
