package de.leuphana.shop.structure.customer;


public class CartItem {
	private int cartItemId;
	private Integer articleId;
	private int quantity;
	private Float price;
	private String name;



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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}