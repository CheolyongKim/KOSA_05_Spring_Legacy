package DI_06_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		// Controller라고 가정
//		ArticleService articleService = new ArticleService(new OracleDAO());
//		Article article = new Article();
//		articleService.write(article);
		
		// 1. Container(Spring memory) 만들기		

		// 2. Container 안에서 생성될 객체를 제공하는 방법이 여러가지(예를 들어 xml)
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_06_Spring/DI_06.xml");

		// 3. Container 안의 객체를 사용하면 된다.
		ArticleService articleService = context.getBean("articleService", ArticleService.class);
		articleService.write(new Article());
	}

}
