package de.leuphana.connector;

import de.leuphana.order.structure.Order;
import de.leuphana.shop.behaviour.ShopService;

import de.leuphana.shop.structure.article.Article;
import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.CD;
import de.leuphana.shop.structure.billing.Invoice;
import de.leuphana.shop.structure.customer.Cart;
import de.leuphana.shop.structure.customer.CartItem;
import de.leuphana.shop.structure.customer.Customer;
import de.leuphana.shop.structure.sales.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/dispatchAction")
@SessionAttributes({"customerId", "cart"})
public class WebShopRestController {

    @Autowired
    ShopService shopService;


    @RequestMapping("/showCatalog")
    public String showCatalog(Model model) {
        if (model.getAttribute("customerId") == null || model.getAttribute("cart") == null) {
            Integer customerId = shopService.createCustomer("Test", "Test"); // TODO: The Parameters need to be changed.
            Cart cart = shopService.getCustomer(customerId).getCart();
            model.addAttribute("customerId", customerId); // TODO check if every model.addAttribute is really needed
            model.addAttribute("cart", cart);
        }
        Catalog catalog = new Catalog();
        Set<Article> articles = new HashSet<>(shopService.getArticles());
        catalog.setArticles(articles); // after getting them from the Database

        model.addAttribute("catalog", catalog);
        model.addAttribute("articles", articles);
        return "catalog";
    }

    @PostMapping("/addArticle")
    public String addArticle(Model model, @RequestParam(name = "articleId") String articleId,
                             @RequestParam(name = "articleQuantity") Integer articleQuantity) {
        Article article = shopService.getArticleByArticleId(Integer.parseInt(articleId));
        Set<Article> articles = new HashSet<>(shopService.getArticles());
        // TODO: articleQuantity needs to be added in URL-call in view.
        Customer customer = shopService.addArticleToCart((Integer)model.getAttribute("customerId"), article.getArticleId(), articleQuantity);
        model.addAttribute("cart", customer.getCart());
        model.addAttribute("article", article);
        model.addAttribute("articles", articles);
        return showCatalog(model);
    }

    @RequestMapping("/showArticle")
    public String showArticle(Model model, @RequestParam(name = "articleId") String articleId) {
        Article article = shopService.getArticleByArticleId(Integer.parseInt(articleId));
        model.addAttribute("article", article);
        if (article instanceof Book book) {
            model.addAttribute("book", book);
        } else if (article instanceof CD cd) {
            model.addAttribute("cd", cd);
        }
        return "article";
    }

    @RequestMapping("/showCart")
    public String showCart(Model model) {
        Cart cart = shopService.getCustomer((Integer)model.getAttribute("customerId")).getCart();
        Collection<CartItem> cartItems = cart.getCartItems();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @RequestMapping("/removeArticle")
    public String removeArticle(Model model, @RequestParam(name = "articleId") String articleId) {
        shopService.decrementArticleQuantityInCart((Integer)model.getAttribute("customerId"), Integer.parseInt(articleId));
        return showCart(model);
    }

    @RequestMapping("/orderArticles")
    public String orderArticles(Model model) {
        Customer customer = shopService.getCustomer((Integer) model.getAttribute("customerId"));
        Cart cart = customer.getCart();
        Collection<CartItem> cartItems = cart.getCartItems();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cart", cart);
        return "order";
    }

    @RequestMapping("/showReceipt")
    public String showReceipt(Model model, @RequestParam(name = "name") String name, @RequestParam(name = "iban") String iban) {
        Integer customerId = (Integer) model.getAttribute("customerId");
        Customer customer = shopService.getCustomer(customerId);
        Order order = shopService.checkOutCart(customer.getCustomerId());

        Invoice invoice = shopService.createInvoice(order.getOrderId());
        model.addAttribute("name", name);
        model.addAttribute("iban", iban);
        model.addAttribute("invoice", invoice);
        return "receipt";
    }
}
