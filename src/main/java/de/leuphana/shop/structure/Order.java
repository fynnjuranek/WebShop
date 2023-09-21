package de.leuphana.shop.structure;

import java.math.BigDecimal;
import java.util.Map;

public class Order {

	private int orderId;
	private int customerId;
	private Map<Integer, OrderPosition> orderPositions;

	public Order(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getOrderId() {
		return orderId;
	}

	public Map<Integer, OrderPosition> getOrderPositions() {
		return orderPositions;
	}

	public void addOrderPosition(OrderPosition orderPosition) {
		orderPositions.put(orderPosition.getPositionId(), orderPosition);
	}

	public int getNumberOfArticles() {
		// TODO Auto-generated method stub
		return 0;
	}

	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice = new BigDecimal("0.0");

		Article article;
		for (OrderPosition orderPosition : orderPositions.values()) {
			article = orderPosition.getArticle();

			totalPrice = totalPrice.add(article.getPrice().multiply(new BigDecimal(orderPosition.getArticleQuantity())));
		}

		return totalPrice;
	}

}