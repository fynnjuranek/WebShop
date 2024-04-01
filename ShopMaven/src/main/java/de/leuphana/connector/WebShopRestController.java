package de.leuphana.connector;

import de.leuphana.article.structure.Article;
import de.leuphana.article.structure.Book;
import de.leuphana.article.structure.CD;
import de.leuphana.customer.structure.Cart;
import de.leuphana.customer.structure.CartItem;
import de.leuphana.shop.behaviour.ShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    private final Shop onlineShop = Shop.create();
    @Autowired
    ShopService shopService;


    @RequestMapping("/showCatalog")
    public String showCatalog(Model model) {
//        if (model.getAttribute("customerId") == null || model.getAttribute("cart") == null) {
//            Integer customerId = shopService.createCustomer("Test", "Test"); // TODO: The Parameters need to be changed.
//            Cart cart = shopService.getCustomer(customerId).getCart();
//            model.addAttribute("customerId", customerId); // TODO check if every model.addAttribute is really needed
//            model.addAttribute("cart", cart);
//        }
//        Catalog catalog = new Catalog();
//        //catalog.getArticles();
//        Set<Article> articles = new HashSet<>(shopService.getArticles());
//        catalog.setArticles(articles); // after getting them from the Database
//
//        model.addAttribute("catalog", catalog);
//        model.addAttribute("articles", articles);
        return "catalog";
    }

    @RequestMapping("/addArticle")
    public String addArticle(Model model, @RequestParam(name = "articleId") String articleId,
                             @RequestParam(name = "articleQuantity") Integer articleQuantity) {
        Article article = shopService.getArticleByArticleId(Integer.parseInt(articleId));
        Set<Article> articles = new HashSet<>(shopService.getArticles());
        // TODO: articleQuantity needs to be added in URL-call in view.
        shopService.addArticleToCart((Integer)model.getAttribute("customerId"), article.getArticleId(), articleQuantity);
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
        Cart cart = shopService.getCustomer((Integer) model.getAttribute("customerId")).getCart();
        model.addAttribute("cartItems", cart.getCartItems());
        return "order";
    }

    @RequestMapping("/showReceipt")
    public String showReceipt(Model model, @RequestParam(name = "name") String name, @RequestParam(name = "iban") String iban) {
        Integer customerId = (Integer) model.getAttribute("customerId");
        Cart cart = shopService.getCustomer(customerId).getCart();
        model.addAttribute("name", name);
        model.addAttribute("iban", iban);
        model.addAttribute("cartItems", cart.getCartItems());
        return "receipt";
    }
}
