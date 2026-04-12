package com.bartmilan.e_comm_api.dto;

import com.bartmilan.e_comm_api.model.DeliveryType;
import com.bartmilan.e_comm_api.model.OrderItem;
import com.bartmilan.e_comm_api.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {

    private Long id;
    private Long userId;
    private OrderStatus status;
    private DeliveryType deliveryType;
    private String street;
    private String city;
    private String zipCode;
    private String country;
    private String parcelLockerId;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private List<OrderItemDto> items;

    public record OrderItemDto(
            Long productId,
            String productName,
            Integer quantity,
            BigDecimal priceAtPurchase
    ) {
    }

    public OrderResponseDto(Long id, Long userId, OrderStatus status, DeliveryType deliveryType, String street,
                            String city, String zipCode, String country, String parcelLockerId, BigDecimal totalPrice,
                            LocalDateTime createdAt, List<OrderItemDto> items) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.deliveryType = deliveryType;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.parcelLockerId = parcelLockerId;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    public String getParcelLockerId() {
        return parcelLockerId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }
}
