package com.bartmilan.e_comm_api.controller;

import com.bartmilan.e_comm_api.dto.OrderResponseDto;
import com.bartmilan.e_comm_api.dto.PlaceOrderRequest;
import com.bartmilan.e_comm_api.model.Order;
import com.bartmilan.e_comm_api.model.OrderStatus;
import com.bartmilan.e_comm_api.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody PlaceOrderRequest request) {
        Order order = orderService.placeOrder(
                request.getUserId(),
                request.getDeliveryType(),
                request.getStreet(),
                request.getCity(),
                request.getZipCode(),
                request.getCountry(),
                request.getParcelLockerId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.toDto(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.toDto(orderService.getById(id)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDto>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrderByUser(userId)
                .stream()
                .map(orderService::toDto)
                .toList()
        );
    }

    @GetMapping("/status")
    public ResponseEntity<List<OrderResponseDto>> getByStatus(@RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.getByStatus(status)
                .stream()
                .map(orderService::toDto)
                .toList()
        );
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<OrderResponseDto> updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.toDto(orderService.updateStatus(id, status)));
    }
}
