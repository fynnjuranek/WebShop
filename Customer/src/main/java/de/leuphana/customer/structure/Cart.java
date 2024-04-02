package de.leuphana.customer.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
	private Integer id;
	private List<CartItem> cartItems;
	private int numberOfArticles;
	private double totalPrice;

	public Cart() {
		cartItems = new ArrayList<>();
		numberOfArticles = 0;
	}

	public void addCartItem(Integer articleId, Float articlePrice, Integer articleQuantity) {
		CartItem cartItem = null;
		for (CartItem cartItemEntity : cartItems) {
			if (Objects.equals(cartItemEntity.getArticleId(), articleId)) {
				cartItem = cartItems.get(cartItems.indexOf(cartItemEntity));
//				cartItem.incrementQuantity();
			}
		}
		if (cartItem == null) {
			cartItem = new CartItem();
			cartItem.setArticleId(articleId);
			cartItem.setPrice(articlePrice);
		}
		cartItem.setQuantity(articleQuantity);
		cartItems.add(cartItem);
		numberOfArticles += articleQuantity;
	}

	public void deleteCartItem(int articleId) {
		for (CartItem cartItem : cartItems) {
			if (cartItem.getArticleId() == (articleId)) {
				cartItems.remove(cartItem);
				numberOfArticles -= cartItem.getQuantity();
				break;
			}
		}
	}

	public void decrementArticleQuantity(Integer articleId) {
		for (CartItem cartItem : cartItems) {
			if (Objects.equals(cartItem.getArticleId(), articleId)) {
				cartItem.decrementQuantity();
				if (cartItem.getQuantity() <= 0)
					cartItems.remove(cartItem);
				numberOfArticles--;
				break;
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumberOfArticles(int numberOfArticles) {
		this.numberOfArticles = numberOfArticles;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public int getNumberOfArticles() {
		return numberOfArticles;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double calculateTotalPrice() {
		totalPrice = 0.0;

		for (CartItem cartItem : getCartItems()) {
			totalPrice += cartItem.getQuantity() * cartItem.getPrice();
		}

		return totalPrice;
	}

}