package de.leuphana.customer.structure;


public class CartItem {
	private int cartItemId;
	private Integer articleId;
	private int quantity;
	private double price;

	public CartItem() {
		quantity = 1;
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void incrementQuantity() {
		quantity++;
	}
	
	public void decrementQuantity() {
		quantity--;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}