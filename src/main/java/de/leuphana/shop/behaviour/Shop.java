package de.leuphana.shop.behaviour;

import de.leuphana.shop.structure.*;
import de.leuphana.webshop.connector.dbconnector.ArticleDatabaseLoader;
import de.leuphana.webshop.connector.dbconnector.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Shop {
	private Catalog catalog;
	private Map<Integer, Customer> customers;
	private static Integer customerId;
	private static Shop onlineShop;

	//private Set<Article> articles;

	public static Shop create() {
		if (onlineShop == null) {
			onlineShop = new Shop();
		}
		return onlineShop;
	}

	private Shop(){
		customers = new HashMap<Integer, Customer>();
		catalog = new Catalog();
		//catalog.setArticles();
		//articles = new HashSet<Article>();

//		Book book = new Book(1);
//		book.setName("Entwickeln von Web-Anwendungen");
//		book.setPrice(23.0f);
//		book.setBookCategory(BookCategory.POPULAR_SCIENCE);
//
//		articles.add(book);
//
//		book = new Book(2);
//		book.setName("Java in a nutshell");
//		book.setPrice(10.5f);
//		book.setBookCategory(BookCategory.POPULAR_SCIENCE);
//
//		articles.add(book);
//
//		book = new Book(3);
//		book.setName("Servlets");
//		book.setPrice(16.5f);
//		book.setBookCategory(BookCategory.POPULAR_SCIENCE);
//
//		articles.add(book); // needs to be implemented
//		catalog.setArticles(articles);

	}

	public Integer createCustomerWithCart() {
		Cart cart = new Cart();

		Customer customer = new Customer(cart);

		customers.put(customer.getCustomerId(), customer);

		return customer.getCustomerId();
	}

	public Article getArticle(int articleId) {
		// Delegation
		return catalog.getArticle(articleId);
	}
	
	public Set<Article> getArticles() {
		return catalog.getArticles();
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void removeArticleFromCart(Integer customerId, int articleId) {
		// Delegation
		Cart cart = customers.get(customerId).getCart();

		cart.deleteCartItem(articleId);
	}

	public void addArticleToCart(Integer customerId, Integer articleId) {
		Article foundArticle = getArticle(articleId);

		Cart cart = customers.get(customerId).getCart();

		cart.addCartItem(foundArticle);
	}

	public void decrementArticleQuantityInCart(Integer customerId,
			Integer articleId) {
		Cart cart = customers.get(customerId).getCart();

		cart.decrementArticleQuantity(articleId);
	}

	public Order checkOutCart(int customerId) {

		Customer customer = customers.get(customerId);
		Cart cart = customer.getCart();

		Order order = new Order(1);

		int i = 1;

		for (CartItem cartItem : cart.getCartItems()) {
			OrderPosition orderPosition = new OrderPosition(i++);
			orderPosition.setArticle(cartItem.getArticle());
			orderPosition.setArticleQuantity(cartItem.getQuantity());
			order.addOrderPosition(orderPosition);
		}

		customer.addOrder(order);

		return order;
	}

	public Cart getCartForCustomer(Integer customerId) {
		return customers.get(customerId).getCart();
	}

}