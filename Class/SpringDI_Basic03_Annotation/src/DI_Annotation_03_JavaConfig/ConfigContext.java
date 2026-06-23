package DI_Annotation_03_JavaConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration	// 이 Java 파일은 DI.xml과 같은 역할(객체의 생성과 조립)을 Spring IOC에서 하게 된다.
public class ConfigContext {
	// xml: <bean id="user" class="...User"
	// xml에서의 bean 설정은 java 코드에서는 함수를 만들고 함수에서 객체를 반환하는 식으로 적용된다.
	@Bean
	public User user() {
		return new User();
	}
	
	// xml: <bean id="user2" class="...User2"
	@Bean
	public User2 user2() {
		return new User2(); 
	}
}
