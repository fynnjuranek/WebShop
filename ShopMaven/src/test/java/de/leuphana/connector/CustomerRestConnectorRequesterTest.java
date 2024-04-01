package de.leuphana.connector;



import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.BookCategory;
import de.leuphana.shop.structure.article.CD;
import de.leuphana.shop.structure.customer.CartItem;
import de.leuphana.shop.structure.customer.Customer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerRestConnectorRequesterTest {

    @Autowired
    CustomerRestConnectorRequester customerRestConnectorRequester;

    static Customer savedCustomer;
    static Book book, book2;
    static String orderId;

    @BeforeAll
    static void beforeAll() {
        book = new Book();
        book.setArticleId(1);
        book.setName("THINKING, FAST AND SLOW");
        book.setManufacturer("Penguin Verlag");
        book.setAuthor("Steven Pinker");
        book.setPrice(16.0f);
        book.setBookCategory(BookCategory.NON_FICTION);

        book2 = new Book();
        book2.setArticleId(2);
        book2.setName("Entwickeln von Web-Anwendungen");
        book2.setPrice(23.0f);
        book2.setBookCategory(BookCategory.POPULAR_SCIENCE);

        orderId = "TEST ORDER ID";

    }

    @Test
    @Order(1)
    void canCustomerBeAdded() {
        String customerName = "Test";
        String customerAddress = "Test address";
        Customer createdCustomer = new Customer(customerName, customerAddress);
        savedCustomer = customerRestConnectorRequester.addCustomer(createdCustomer);
        System.out.println("Added customer with customerId: " + savedCustomer.getCustomerId() + " and name: " + savedCustomer.getName());
        Assertions.assertNotNull(savedCustomer);
    }

    @Test
    @Order(3)
    void canArticleBeAddedToCart() {
        CD cd = new CD();
        cd.setArticleId(3);
        cd.setArtist("Vampire Weekend");
        cd.setPrice(13.0f);
        cd.setName("Vampire Weekend");
        cd.setManufacturer("Test producer");
        Integer articleQuantity = 5;

        customerRestConnectorRequester.addArticleToCart(savedCustomer.getCustomerId(), book.getArticleId(), book.getPrice(), articleQuantity);
        customerRestConnectorRequester.addArticleToCart(savedCustomer.getCustomerId(), book2.getArticleId(), book2.getPrice(), articleQuantity);
        Customer customer = customerRestConnectorRequester.addArticleToCart(savedCustomer.getCustomerId(), cd.getArticleId(), cd.getPrice(), articleQuantity);
//        Cart cart = savedCustomer.getCart();
//        cart.addCartItem(book, 3);
//        cart.addCartItem(book2, 2);
//        cart.addCartItem(cd, articleQuantity);
        System.out.println("New cart: ");
        for (CartItem cartItem : customer.getCart().getCartItems()) {
            System.out.println(cartItem.getArticleId());
            System.out.println(cartItem.getPrice());
        }
    }

    @Test
    @Order(2)
    void canOrderBeAddedToCustomer() {
        Customer customer = customerRestConnectorRequester.addOrderToCustomer(savedCustomer.getCustomerId(), orderId);
        System.out.println("Added orders to customer with id: " + savedCustomer.getCustomerId() + " and orderId: " + orderId);
        System.out.println("Order id's: ");
        for (String id : customer.getOrderIDs()) {
            System.out.println(id);
        }
        Assertions.assertNotNull(customer.getOrderIDs());
    }




//    @Test
//    @Order(4)
//    void canCustomerBeDeleted() {
//        boolean isCustomerDeleted = customerRestConnectorRequester.deleteCustomer(savedCustomer.getCustomerId());
//        Assertions.assertTrue(isCustomerDeleted);
//    }



}
