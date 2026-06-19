package DI_06_Spring;

public class OracleDAO implements ArticleDAO {

	@Override
	public void insert(Article article) {
		System.out.println("Oracle insert");
	}

}
