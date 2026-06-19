package DI_06_Spring;

public class MysqlDAO implements ArticleDAO {

	@Override
	public void insert(Article article) {
		System.out.println("Mysql insert");
	}

}
