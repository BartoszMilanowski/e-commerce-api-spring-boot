package com.bartmilan.e_comm_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal priceAtAddition;

    public CartItem() {
    }

    public CartItem(Cart cart, Product product, Integer quantity, BigDecimal priceAtAddition) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.priceAtAddition = priceAtAddition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceAtAddition() {
        return priceAtAddition;
    }

    public void setPriceAtAddition(BigDecimal priceAtAddition) {
        this.priceAtAddition = priceAtAddition;
    }
}
