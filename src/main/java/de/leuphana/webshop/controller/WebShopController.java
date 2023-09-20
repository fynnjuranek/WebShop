package de.leuphana.webshop.controller;

import de.leuphana.shop.behaviour.Shop;
import de.leuphana.shop.structure.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Collection;
import java.util.Set;

@Controller
@RequestMapping("/dispatchAction") // TODO vielleicht geht das
@SessionAttributes({"customerId", "cart"})
public class WebShopController {

    private Shop onlineShop = Shop.create();


    @RequestMapping("/showCatalog")
    public String showCatalog(Model model) {
        if (model.getAttribute("customerId") == null || model.getAttribute("cart") == null) {
            Integer customerId = onlineShop.createCustomerWithCart();
            Cart cart = onlineShop.getCartForCustomer(customerId);
            model.addAttribute("customerId", customerId);
            model.addAttribute("cart", cart);
        }
        Catalog catalog = onlineShop.getCatalog();
        Set<Article> articles = catalog.getArticles();
        model.addAttribute("catalog", catalog);
        model.addAttribute("articles", articles);
        return "catalog";
    }

    @RequestMapping("/addArticle")
    public String addArticle(Model model, @RequestParam(name = "articleId") String articleId) {
        Article article = onlineShop.getCatalog().getArticle(Integer.parseInt(articleId));
        Set<Article> articles = onlineShop.getCatalog().getArticles();
        onlineShop.addArticleToCart((Integer)model.getAttribute("customerId"), article.getArticleId());
        model.addAttribute("article", article);
        model.addAttribute("articles", articles);
        return "catalog";
    }

    @RequestMapping("/showArticle")
    public String showArticle(Model model, @RequestParam(name = "articleId") String articleId) {
        Article article = onlineShop.getCatalog().getArticle(Integer.parseInt(articleId));
        model.addAttribute("article", article);
        if (article instanceof Book) {
            Book book = (Book) article;
            model.addAttribute("book", book);
        } else if (article instanceof CD) {
            CD cd = (CD) article;
            model.addAttribute("cd", cd);
        }
        return "article";
    }

    @RequestMapping("/showCart")
    public String showCart(Model model) {
        Cart cart = onlineShop.getCartForCustomer((Integer)model.getAttribute("customerId"));
        Collection<CartItem> cartItems = cart.getCartItems();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @RequestMapping("/removeArticle")
    public String removeArticle(Model model, @RequestParam(name = "articleId") String articleId) {
        onlineShop.decrementArticleQuantityInCart((Integer)model.getAttribute("customerId"), Integer.parseInt(articleId));
        return showCart(model);
    }

    @RequestMapping("/orderArticles")
    public String orderArticles(Model model) {
        Cart cart = onlineShop.getCartForCustomer((Integer) model.getAttribute("customerId"));
        model.addAttribute("cartItems", cart.getCartItems());
        return "order";
    }

    @RequestMapping("/showReceipt")
    public String showReceipt(Model model, @RequestParam(name = "name") String name, @RequestParam(name = "iban") String iban) {
        Integer customerId = (Integer) model.getAttribute("customerId");
        Cart cart = onlineShop.getCartForCustomer(customerId);
        model.addAttribute("name", name);
        model.addAttribute("iban", iban);
        model.addAttribute("cartItems", cart.getCartItems());
        return "receipt";
    }







}
