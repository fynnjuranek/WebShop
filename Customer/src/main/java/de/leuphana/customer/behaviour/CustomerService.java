package de.leuphana.customer.behaviour;
import de.leuphana.customer.structure.database.entity.CartEntity;
import de.leuphana.customer.structure.database.entity.CartItemEntity;
import de.leuphana.customer.structure.database.entity.CustomerEntity;
import de.leuphana.customer.structure.database.CustomerDatabase;

import de.leuphana.customer.structure.database.mapper.CustomerMapper;
import de.leuphana.customer.structure.Cart;
import de.leuphana.customer.structure.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerDatabase customerDatabase;

    @Autowired
    CustomerMapper customerMapper;

    static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    public Customer createCustomer(String customerName, String customerAddress, String customerEmail, String password) {
        Customer customer = new Customer(customerName, customerAddress, customerEmail, password);
        System.out.println(customer.getPassword());
        return addCustomerToDatabase(customer);
    }

    public Customer createCustomer(Customer customer) {
        return addCustomerToDatabase(customer);
    }

    public Customer addCustomerToDatabase(Customer customer){
        CustomerEntity customerEntity = customerMapper.mapToCustomerEntity(customer);
        CartEntity cartEntity = customerMapper.mapToCartEntity(customer.getCart());
        customerEntity.setCartEntity(cartEntity);

        // This is used to set the relationship between CartEntity and CartItemEntity because otherwise
        // "cartEntity" would be null in cartItemEntity -> it would be deleted (orphan)
        if (cartEntity.getCartItems() != null) {
            for (CartItemEntity cartItemEntity : cartEntity.getCartItems()) {
                cartItemEntity.setCartEntity(cartEntity);
            }
        }

        // To be 100% sure that the customer got properly saved!
        CustomerEntity savedCustomer = customerDatabase.save(customerEntity);
        LOGGER.info("Customer with customerId {} got saved to the database", savedCustomer.getCustomerId());
        // This needs to be explicitly set because otherwise the cart is null in "mappedCustomer"
        Customer mappedCustomer = customerMapper.mapToCustomer(savedCustomer);
        mappedCustomer.setCart(customerMapper.mapToCart(cartEntity));
        return mappedCustomer;
    }


    public Customer addArticleToCart(Integer customerId, Integer articleId, Float articlePrice, Integer articleQuantity){
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByCustomerId(customerId);
        CartEntity cartEntity = customerEntity.getCartEntity();
        Cart cart = customerMapper.mapToCart(cartEntity);
        cart.addCartItem(articleId, articlePrice, articleQuantity);
        return updateCart(cart, customerId);
    }

    public Customer removeArticleFromCart(Integer customerId, Integer articleId) {
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByCustomerId(customerId);
        CartEntity cartEntity = customerEntity.getCartEntity();
        Cart cart = customerMapper.mapToCart(cartEntity);
        cart.deleteCartItem(articleId);
        return updateCart(cart, customerId);
    }

    public Customer addOrderToCustomer(Integer customerId, String orderId) {
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByCustomerId(customerId);
        CartEntity cartEntity = customerEntity.getCartEntity();
        Customer customer = customerMapper.mapToCustomer(customerEntity);
        customer.addOrder(orderId);

        CustomerEntity mappedCustomer = customerMapper.mapToCustomerEntity(customer);
        mappedCustomer.setCartEntity(cartEntity);
        return customerMapper.mapToCustomer(customerDatabase.save(mappedCustomer));
    }

    public Customer decrementArticleQuantityInCart(Integer customerId, Integer articleId) {
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByCustomerId(customerId);
        CartEntity cartEntity = customerEntity.getCartEntity();
        Cart cart = customerMapper.mapToCart(cartEntity);
        cart.decrementArticleQuantity(articleId);
        return updateCart(cart, customerId);
    }


    public Customer findCustomerByCustomerId(Integer customerId) {
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByCustomerId(customerId);
        Customer customer = customerMapper.mapToCustomer(customerEntity);
        // customer orders needs to be set separately because it's null otherwise
        if (customerEntity.getOrderIDs() != null) {
            customer.setOrders(customerEntity.getOrderIDs());
        }
        customer.setCart(customerMapper.mapToCart(customerEntity.getCartEntity()));

        return customer;
    }

    public Customer findCustomerByCustomerEmail(String customerEmail) {
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByCustomerEmail(customerEmail);
        Customer customer = customerMapper.mapToCustomer(customerEntity);
        // customer orders needs to be set separately because it's null otherwise
        if (customerEntity.getOrderIDs() != null) {
            customer.setOrders(customerEntity.getOrderIDs());
        }
        customer.setCart(customerMapper.mapToCart(customerEntity.getCartEntity()));

        return customer;
    }

    public List<Customer> findAllCustomers() {
        List<CustomerEntity> customerEntities = customerDatabase.findAll();
        List<Customer> customers = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntities){
            customers.add(customerMapper.mapToCustomer(customerEntity));
        }
        return customers;
    }

    public boolean deleteCustomerByCustomerId(Integer customerId) {
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByCustomerId(customerId);
        customerDatabase.deleteById(customerEntity.getCustomerId());
        boolean isDeleted = false;
        if (customerDatabase.findCustomerEntityByCustomerId(customerId) == null) {
            isDeleted = true;
            LOGGER.info("Customer with customerId {} got successfully deleted", customerId);
        }
        return isDeleted;
    }

    private Customer updateCart(Cart cart, Integer customerId) {
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByCustomerId(customerId);
        // Set the id of normal cart before mapping to entity because otherwise the id will be new generated, and it needs to stay the same!
        cart.setId(customerEntity.getCartEntity().getId());
        cart.calculateTotalPrice();
        CartEntity cartEntity = customerMapper.mapToCartEntity(cart) ;
        for (CartItemEntity cartItemEntity : cartEntity.getCartItems()) {
            cartItemEntity.setCartEntity(cartEntity);
        }
        customerEntity.setCartEntity(cartEntity);
        CustomerEntity savedCustomer = customerDatabase.save(customerEntity);
        LOGGER.info("Saved customer (id {}) with updated cart to database",  savedCustomer.getCustomerId());
        Customer mappedCustomer = customerMapper.mapToCustomer(savedCustomer);
        mappedCustomer.setCart(customerMapper.mapToCart(cartEntity));

        return mappedCustomer;
    }
}
