package com.bartmilan.e_comm_api.service;

import com.bartmilan.e_comm_api.dto.OrderResponseDto;
import com.bartmilan.e_comm_api.exception.ResourceNotFoundException;
import com.bartmilan.e_comm_api.model.*;
import com.bartmilan.e_comm_api.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    @Transactional
    public Order placeOrder(Long userId, DeliveryType deliveryType, String street, String city, String zipCode,
                            String country, String parcelLockerId) {

        Cart cart = cartService.getCart(userId);

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cannot place order with empty cart");
        }

        Order order = new Order(userId, OrderStatus.PENDING, deliveryType, street, city, zipCode, country,
                parcelLockerId, BigDecimal.ZERO);

        orderRepository.save(order);

        List<OrderItem> orderItems = cart.getItems().stream()
                .map(cartItem -> new OrderItem(
                        order,
                        cartItem.getProduct(),
                        cartItem.getQuantity(),
                        cartItem.getPriceAtAddition()
                ))
                .collect(Collectors.toList());

        order.setItems(orderItems);

        BigDecimal totalPrice = orderItems.stream()
                .map(item -> item.getPriceAtPurchase()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

        cartService.clearCart(userId);

        return order;
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public List<Order> getOrderByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
    }

    public List<Order> getByStatus(OrderStatus status){
        return orderRepository.findByStatus(status);
    }

    public Order updateStatus(Long id, OrderStatus status){
        Order order = getById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public OrderResponseDto toDto(Order order){
        List<OrderResponseDto.OrderItemDto> itemDtos = order.getItems().stream()
                .map(item -> new OrderResponseDto.OrderItemDto(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPriceAtPurchase()
                ))
                .toList();

        return new OrderResponseDto(
                order.getId(),
                order.getUserId(),
                order.getStatus(),
                order.getDeliveryType(),
                order.getStreet(),
                order.getCity(),
                order.getZipCode(),
                order.getCountry(),
                order.getParcelLockerId(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                itemDtos
        );
    }
}
