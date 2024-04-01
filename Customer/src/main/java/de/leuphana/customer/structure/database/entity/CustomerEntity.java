package de.leuphana.customer.structure.database.entity;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String name;
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    private CartEntity cartEntity;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> orderIDs;

    public CustomerEntity() {
    }

    public CustomerEntity(String name, String address, CartEntity cartEntity, List<String> orders) {
        this.name = name;
        this.address = address;
        this.cartEntity = cartEntity;
        this.orderIDs = orders;
    }


    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CartEntity getCartEntity() {
        return cartEntity;
    }

    public void setCartEntity(CartEntity cartEntity) {
        this.cartEntity = cartEntity;
    }

    public List<String> getOrderIDs() {
        return orderIDs;
    }

    public void setOrderIDs(List<String> orderIDs) {
        this.orderIDs = orderIDs;
    }
}
