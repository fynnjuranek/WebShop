package de.leuphana.shop.structure.billing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Invoice {
	private Map<Integer, InvoicePosition> invoicePositions;
	private String orderId;

	public Invoice() {
		invoicePositions = new HashMap<Integer, InvoicePosition>();
	}
	
	public void addInvoicePosition(InvoicePosition invoicePosition) {
		invoicePositions.put(invoicePosition.getPositionId(), invoicePosition);
	}

	public List<InvoicePosition> getAllInvoicePositions()  {
		return invoicePositions.values().stream().toList();
	}

	public int getInvoiceQuantity() {
		int invoiceQuantity = 0;
		for (InvoicePosition invoicePosition : invoicePositions.values()) {
			invoiceQuantity += invoicePosition.getArticleQuantity();
		}
		return invoiceQuantity;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public double getTotalPrice() {
		double totalPrice = 0.0;
		for (InvoicePosition invoicePosition : invoicePositions.values()) {
			totalPrice += invoicePosition.getTotalPrice();
		}
		return totalPrice;
	}
}