package DI_06_Spring;

// OracleDAO, MysqlDAO의 구현을 강제한다.
public interface ArticleDAO {
	// CRUD
	void insert(Article article);
}
