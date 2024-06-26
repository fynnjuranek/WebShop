package de.leuphana.shop.structure.sales;



import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.BookCategory;

import java.util.HashSet;
import java.util.Set;


public class Catalog {

	private int catalogId;
	private Set<Article> articles;

	public Catalog() {
		articles = new HashSet<Article>();
		this.catalogId = 1;
		
		Book book = new Book();
		book.setArticleId(1);
		book.setName("Entwickeln von Web-Anwendungen");
		book.setPrice(23.0f);
		book.setBookCategory(BookCategory.POPULAR_SCIENCE);
		
		articles.add(book);
		
		book =  new Book();
		book.setArticleId(2);
		book.setName("Java in a nutshell");
		book.setPrice(10.5f);
		book.setBookCategory(BookCategory.POPULAR_SCIENCE);
		
		articles.add(book);
		
		book =  new Book();
		book.setArticleId(3);
		book.setName("Servlets");
		book.setPrice(16.5f);
		book.setBookCategory(BookCategory.POPULAR_SCIENCE);
		
		articles.add(book);
	}

	public int getCatalogId() {
		return catalogId;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public Article getArticle(int articleId) {
		Article foundArticle = null;

		for (Article article : articles) {
			if (article.getArticleId() == articleId) {
				foundArticle = article;
				break;
			}
		}

		return foundArticle;
	}

}
