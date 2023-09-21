package de.leuphana.shop.structure;

import java.util.HashMap;
import java.util.Map;

public class Customer {
	private static Integer lastGeneratedCustomerId;

	private Integer customerId;
	private String name;
	private String address;
	private Cart cart;
	private Map<Integer, Order> orders;

	public Customer(Cart cart) {
		
		// TODO nullPointerException umgangen (wurde noch nicht initialisiert!
		if (lastGeneratedCustomerId == null) {
			lastGeneratedCustomerId = 0;
		}
		this.customerId = ++lastGeneratedCustomerId;
		this.cart = cart;
		orders = new HashMap<Integer, Order>();
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Cart getCart() {
		return cart;
	}
	
	public void addOrder(Order order) {
		orders.put(order.getOrderId(), order);
	}

}