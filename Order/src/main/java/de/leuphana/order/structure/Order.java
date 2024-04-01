package de.leuphana.order.structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

	private String orderId;
//	private Integer customerId;
	private List<OrderPosition> orderPositions;

	public Order() {
		orderPositions = new ArrayList<OrderPosition>();
	}

//	public int getCustomerId() {
//		return customerId;
//	}

//	public void setCustomerId(Integer customerId) {
//		this.customerId = customerId;
//	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public List<OrderPosition> getOrderPositions() {
		return orderPositions;
	}

	public void setOrderPositions(List<OrderPosition> orderPositions) {
		this.orderPositions = orderPositions;
	}

	public void addOrderPosition(OrderPosition orderPosition) {
		this.orderPositions.add(orderPosition);
	}

	public int getNumberOfArticles() {
		int quantity = 0;
		for (OrderPosition orderPosition : orderPositions) {
			quantity += orderPosition.getArticleQuantity();
		}
		return quantity;
	}
//
//	public double getTotalPrice() {
//		double totalPrice = 0.0;
//
//		for (OrderPosition orderPosition : orderPositions) {
//			totalPrice += orderPosition.getArticleQuantity() * orderPosition.getArticlePrice();
//		}
//
//		return totalPrice;
//	}

}