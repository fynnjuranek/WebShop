package de.leuphana.shop.structure;

public class CartItem {

	private int cartItemId;
	private de.leuphana.shop.structure.Article article;
	private int quantity;

	public CartItem(de.leuphana.shop.structure.Article article) {
		this.article = article;
		quantity = 1;
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public Article getArticle() {
		return article;
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

}