package de.leuphana.connector;


import de.leuphana.shop.structure.customer.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("Customer")
public interface CustomerRestConnectorRequester {

    @RequestMapping("/getCustomer/{customerId}")
    Customer getCustomerByCustomerId(@PathVariable("customerId") Integer customerId);

    @RequestMapping("/getCustomerByEmail/{customerEmail}")
    Customer getCustomerByEmail(@PathVariable("customerEmail") String customerEmail);

    @RequestMapping("/createCustomer/{customerName}/{customerAddress}/{customerEmail}/{password}")
    Customer createCustomer(@PathVariable("customerName") String customerName,
                            @PathVariable("customerAddress") String customerAddress, @PathVariable("customerEmail") String customerEmail,
                            @PathVariable("password") String password);

    @RequestMapping("/createCustomer")
    Customer createCustomer(@RequestBody Customer customer);


    @RequestMapping("/getCustomers")
    List<Customer> getAllCustomers();

    @RequestMapping("/addOrderToCustomer/{customerId}/{orderId}")
    Customer addOrderToCustomer(@PathVariable("customerId") Integer customerId, @PathVariable("orderId") String orderId);

    @RequestMapping("/addArticleToCart")
    Customer addArticleToCart(@RequestParam Integer customerId, @RequestParam Integer articleId,
                              @RequestParam Float articlePrice, @RequestParam Integer articleQuantity);

    @RequestMapping("/removeArticleFromCart/{customerId}/{articleId}")
    Customer removeArticleFromCart(@PathVariable("customerId") Integer customerId, @PathVariable("articleId") Integer articleId);

    @RequestMapping("/decrementArticleQuantity/{customerId}/{articleId}")
    Customer decrementArticleQuantityInCart(@PathVariable("customerId") Integer customerId, @PathVariable("articleId") Integer articleId);

    @PostMapping("/addCustomer")
    Customer addCustomer(@RequestBody Customer customer);

    @RequestMapping("/deleteCustomer/{customerId}")
    boolean deleteCustomer(@PathVariable("customerId") Integer customerId);


}
