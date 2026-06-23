package DI_08_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
//		JobExecute jobExecute = new JobExecute("hong", 100);
//		JobExecute jobExecute2 = new JobExecute("kim", 100L);
//		JobExecute jobExecute3 = new JobExecute("park", "kim");
//		
//		ArticleDAO articleDAO = new ArticleDAO();
//		jobExecute.setArticleDao(articleDAO);
//		jobExecute.setData(500);
		
		// xml로 바꾸기
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_08_Spring/DI_08.xml");
		
	}

}
