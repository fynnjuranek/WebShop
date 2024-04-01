package de.leuphana.customer.structure.database.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cartEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CartItemEntity> cartItems;

    private int numberOfArticles;

    public CartEntity() {
        cartItems = new ArrayList<>();
        numberOfArticles = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCartItems(List<CartItemEntity> cartItems) {
        this.cartItems = cartItems;
    }

    public void setNumberOfArticles(int numberOfArticles) {
        this.numberOfArticles = numberOfArticles;
    }

    public List<CartItemEntity> getCartItems() {
        return cartItems;
    }

    public int getNumberOfArticles() {
        return numberOfArticles;
    }

}
