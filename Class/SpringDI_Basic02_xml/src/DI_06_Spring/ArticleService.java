package DI_06_Spring;

// ArticleService는 ArticleDAO에 (복합연관)의존한다.
//	의존 = 연관관계
public class ArticleService {
	private ArticleDAO articleDAO;
	
	public ArticleService(ArticleDAO articleDAO) {
		// Spring code - 거의 다 interface Polymorphism
		this.articleDAO = articleDAO;
		System.out.println("ArticleService 생성자 호출");
	}
	
	// Service code
	public void write(Article article) {
		this.articleDAO.insert(article);
	}
}
