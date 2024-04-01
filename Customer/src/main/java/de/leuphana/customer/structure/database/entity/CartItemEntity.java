package de.leuphana.customer.structure.database.entity;

import jakarta.persistence.*;

@Entity
public class  CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;

    @ManyToOne(fetch = FetchType.EAGER)
    private CartEntity cartEntity;

    private Integer articleId;
    private int quantity;
    private Float price;

    public CartItemEntity() {
        quantity = 1;
    }

    public CartEntity getCartEntity() {
        return cartEntity;
    }

    public void setCartEntity(CartEntity cartEntity) {
        this.cartEntity = cartEntity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }
}
