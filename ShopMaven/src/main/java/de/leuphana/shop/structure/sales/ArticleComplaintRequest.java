package de.leuphana.shop.structure.sales;

public class ArticleComplaintRequest {
	private Integer orderId;
	private Integer articleId;
	private String reason;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "ArticleComplaintRequest [orderId=" + orderId + ", articleId=" + articleId + ", reason=" + reason + "]";
	}

}
