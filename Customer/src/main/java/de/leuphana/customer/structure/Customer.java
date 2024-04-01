package de.leuphana.customer.structure;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private Integer customerId;
	private String name;
	private String address;
	private Cart cart;
	private List<String> orderIDs;

	public Customer() {
		cart = new Cart();
	}

	public Customer(Cart cart) {
		this.cart = cart;
		orderIDs = new ArrayList<>();
	}

	public Customer(String name, String address) {
		cart = new Cart();
		orderIDs = new ArrayList<>();
		this.name = name;
		this.address = address;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public void setOrderIDs(List<String> orderIDs) {
		this.orderIDs = orderIDs;
	}

	public Cart getCart() {
		return cart;
	}
	
	public void addOrder(String orderId) {
		orderIDs.add(orderId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<String> getOrderIDs() {
		return orderIDs;
	}

	public void setOrders(List<String> orderIDs) {
		this.orderIDs = orderIDs;
	}
}