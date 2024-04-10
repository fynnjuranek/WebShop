package de.leuphana.customer.structure;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private Integer customerId;
	private String customerEmail;
	private String password;
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

	public Customer(String name, String address, String customerEmail, String password) {
		cart = new Cart();
		orderIDs = new ArrayList<>();
		this.name = name;
		this.address = address;
		this.customerEmail = customerEmail;
		this.password = password;
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

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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