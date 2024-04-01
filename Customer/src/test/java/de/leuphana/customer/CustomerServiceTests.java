package de.leuphana.customer;

import de.leuphana.customer.behaviour.CustomerService;
import de.leuphana.customer.structure.Cart;
import de.leuphana.customer.structure.CartItem;
import de.leuphana.customer.structure.Customer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTests {

    @Autowired
    CustomerService customerService;

    static Customer customer;
    static Customer addedCustomer;

    static Integer articleId;
    @BeforeAll
    static void beforeAll() {
        articleId = 1;
        Float articlePrice = 16.0f;
        Integer secondArticleId = 2;
        Float secondArticlePrice = 23.0f;

        Cart cart = new Cart();
        Integer articleQuantity = 1;
        cart.addCartItem(articleId, articlePrice, articleQuantity);
        cart.addCartItem(secondArticleId, secondArticlePrice, articleQuantity);

        String testOrderId = "TEST ORDER ID";

        customer = new Customer(cart);
        customer.addOrder(testOrderId);
        customer.setAddress("Test address");
        customer.setName("Test");
    }

    @AfterAll
    static void afterAll() {
        customer = null;
        addedCustomer = null;
    }

    @Test
    @Order(1)
    void canCustomerBeAdded() {
        addedCustomer = customerService.addCustomerToDatabase(customer);
        System.out.println("Added customer with id: " + addedCustomer.getCustomerId() + " to customer database");
        System.out.println("Customer name: " + addedCustomer.getName() + ", address: " + addedCustomer.getAddress());
        Assertions.assertNotNull(addedCustomer);
    }

    @Test
    @Order(2)
    void canCustomerBeFound() {
        Customer foundCustomer = customerService.findCustomerByCustomerId(addedCustomer.getCustomerId());
        System.out.println("Found customer with name: " + foundCustomer.getName());
        List<CartItem> customerCartItems = foundCustomer.getCart().getCartItems();
        System.out.println("Cart items: ");
        for (CartItem cartItem : customerCartItems) {
            System.out.println("article id: " + cartItem.getArticleId() +
                    ", quantity: " + cartItem.getQuantity() + ", price: " + cartItem.getPrice());
        }
        List<String> orderIDs = foundCustomer.getOrderIDs();
        System.out.println("Order IDs: ");
        for (String orderID : orderIDs) {
            System.out.println("id: " + orderID);
        }
        Assertions.assertNotNull(foundCustomer);
    }

    @Test
    @Order(3)
    void canArticleBeAddedToCart() {
        Integer thirdArticleId = 3;
        Float thirdArticlePrice = 13.0f;
        Integer articleQuantity = 5;

        Customer customer = customerService.addArticleToCart(addedCustomer.getCustomerId(), thirdArticleId, thirdArticlePrice, articleQuantity);

        System.out.println("New cart: ");
        for (CartItem cartItem : customer.getCart().getCartItems()) {
            System.out.println(cartItem.getArticleId());
            System.out.println(cartItem.getPrice());
        }
    }

    @Test
    @Order(4)
    void canArticleBeRemovedFromCart() {
        Customer  foundCustomer = customerService.removeArticleFromCart(addedCustomer.getCustomerId(), articleId);
        boolean isRemoved = true;
        for (CartItem cartItem : foundCustomer.getCart().getCartItems()) {
            if (cartItem.getArticleId().equals(articleId)) {
                System.out.println(cartItem.getArticleId());
                isRemoved = false;
                break;
            }
        }
        System.out.println("Article with id: " + articleId + " has been removed from cart of customer id: " + addedCustomer.getCustomerId());
        System.out.println("(variable) isRemoved: " + isRemoved);
        Assertions.assertTrue(isRemoved);
    }


    @Test
    @Order(5)
    void canCustomerBeDeleted() {
        boolean isCustomerDeleted = customerService.deleteCustomerByCustomerId(addedCustomer.getCustomerId());
        System.out.println("Deleted customer with name: " + addedCustomer.getName() + " got deleted? " + isCustomerDeleted);
        Assertions.assertTrue(isCustomerDeleted);
    }


}
