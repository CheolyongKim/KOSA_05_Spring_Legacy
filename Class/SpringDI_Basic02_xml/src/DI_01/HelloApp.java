package DI_01;

public class HelloApp {
	public static void main(String[] args) {
		MessageBean messageBean = new MessageBean();
		messageBean.sayHello("hong");
	}
}
/*
 * 요구사항
 * 1. 한글버전 (hong) > 안녕 hong
 * 2. 영문버전 (hong) > Hello hong
 * 
 * MessageBean_kr
 * MessageBean_En
 * 방법 1 -> MessageBean을 두 개 만들어서 해결
 * 방법 2 -> interface
 */