package com.bartmilan.e_comm_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryType deliveryType;

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String zipCode;

    @Column
    private String country;

    @Column
    private String parcelLockerId;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {
    }

    public Order(Long userId, OrderStatus status, DeliveryType deliveryType, String street, String city, String zipCode,
                 String country, String parcelLockerId, BigDecimal totalPrice) {
        this.userId = userId;
        this.status = status;
        this.deliveryType = deliveryType;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.parcelLockerId = parcelLockerId;
        this.totalPrice = totalPrice;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getParcelLockerId() {
        return parcelLockerId;
    }

    public void setParcelLockerId(String parcelLockerId) {
        this.parcelLockerId = parcelLockerId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
