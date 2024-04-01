package de.leuphana.order.structure;

import java.io.Serializable;

public class OrderPosition implements Serializable {

	private Integer positionId;
	private Integer articleId;
//	private Float articlePrice;
	private int articleQuantity;

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

//	public Float getArticlePrice() {
//		return articlePrice;
//	}
//
//	public void setArticlePrice(Float articlePrice) {
//		this.articlePrice = articlePrice;
//	}

	public int getArticleQuantity() {
		return articleQuantity;
	}

	public void setArticleQuantity(int articleQuantity) {
		this.articleQuantity = articleQuantity;
	}

}