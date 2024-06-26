package de.leuphana.shop.structure.billing;

public class InvoicePosition {
	private Integer positionId;
	private Integer articleId;
	private Float articlePrice;
	private String articleName;
	private int articleQuantity;

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Float getArticlePrice() {
		return articlePrice;
	}

	public void setArticlePrice(Float articlePrice) {
		this.articlePrice = articlePrice;
	}

	public int getArticleQuantity() {
		return articleQuantity;
	}

	public void setArticleQuantity(int articleQuantity) {
		this.articleQuantity = articleQuantity;
	}

	public Float getTotalPrice() {
		return articleQuantity * articlePrice;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

}