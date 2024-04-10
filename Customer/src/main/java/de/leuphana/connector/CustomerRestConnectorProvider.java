package de.leuphana.connector;

import de.leuphana.customer.behaviour.CustomerService;
import de.leuphana.customer.structure.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CustomerRestConnectorProvider {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/getCustomer/{customerId}")
    public Customer findCustomerByCustomerId(@PathVariable("customerId") Integer customerId) {
        return customerService.findCustomerByCustomerId(customerId);
    }

    @RequestMapping("/getCustomerByEmail/{customerEmail}")
    Customer getCustomerByEmail(@PathVariable("customerEmail") String customerEmail) {
        return customerService.findCustomerByCustomerEmail(customerEmail);
    }

    @RequestMapping("/createCustomer/{customerName}/{customerAddress}/{customerEmail}/{password}")
    Customer createCustomer(@PathVariable("customerName") String customerName,
                            @PathVariable("customerAddress") String customerAddress, @PathVariable("customerEmail") String customerEmail,
                            @PathVariable("password") String password) {
        return customerService.createCustomer(customerName, customerAddress, customerEmail, password);
    }

    @RequestMapping("/createCustomer")
    Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @RequestMapping("/getCustomers")
    public List<Customer> findAllCustomers() { return customerService.findAllCustomers(); }

    @PostMapping("/addCustomer")
    public Customer addCustomerToDatabase(@RequestBody Customer customer) {
        return customerService.addCustomerToDatabase(customer);
    }

    @RequestMapping("/addOrderToCustomer/{customerId}/{orderId}")
    public Customer addOrderToCustomer(@PathVariable("customerId") Integer customerId, @PathVariable("orderId") String orderId) {
        return customerService.addOrderToCustomer(customerId, orderId);
    }

    @RequestMapping("/decrementArticleQuantity/{customerId}/{articleId}")
    public Customer decrementArticleQuantityInCart(@PathVariable("customerId") Integer customerId, @PathVariable("articleId") Integer articleId) {
        return customerService.decrementArticleQuantityInCart(customerId, articleId);
    }


    @RequestMapping("/removeArticleFromCart/{customerId}/{articleId}")
    Customer removeArticleFromCart(@PathVariable("customerId") Integer customerId, @PathVariable("articleId") Integer articleId) {
        return customerService.removeArticleFromCart(customerId, articleId);
    }


    @RequestMapping("/addArticleToCart")
    Customer addArticleToCart(@RequestParam Integer customerId, @RequestParam Integer articleId,
                              @RequestParam Float articlePrice, @RequestParam Integer articleQuantity) {
        return customerService.addArticleToCart(customerId, articleId, articlePrice, articleQuantity);
    }

    @RequestMapping("/deleteCustomer/{customerId}")
    public boolean deleteCustomerByCustomerId(@PathVariable("customerId") Integer customerId) {
        return customerService.deleteCustomerByCustomerId(customerId);
    }

}
