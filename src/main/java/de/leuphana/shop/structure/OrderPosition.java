package de.leuphana.shop.structure;

public class OrderPosition {

	private int positionId;
	private de.leuphana.shop.structure.Article article;
	private int articleQuantity;

	public OrderPosition(int positionId) {
		this.positionId = positionId;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public de.leuphana.shop.structure.Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getArticleQuantity() {
		return articleQuantity;
	}

	public void setArticleQuantity(int articleQuantity) {
		this.articleQuantity = articleQuantity;
	}

}