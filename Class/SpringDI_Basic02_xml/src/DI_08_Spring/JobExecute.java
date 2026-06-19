package DI_08_Spring;

public class JobExecute {
	
	public JobExecute(String first , int second) {
		System.out.println("String , int");
	}
	public JobExecute(String first , long second) {
		System.out.println("String , long");
	}
	public JobExecute(String first , String second) {
		System.out.println("String , String");
	}
	
	private ArticleDAO articleDAO;

	public ArticleDAO getArticleDAO() {
		return articleDAO;
	}
	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
		System.out.println("setArticleDAO : " + this.articleDAO);
	}
	
	
	public int data;

	public void setData(int data) {
		this.data = data;
		System.out.println("setData : " + this.data);
	}
	
	
}