package com.bartmilan.e_comm_api.controller;

import com.bartmilan.e_comm_api.model.Cart;
import com.bartmilan.e_comm_api.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping("/{userId}/items/{productId}")
    public ResponseEntity<Cart> addItem(@PathVariable Long userId, @PathVariable Long productId, @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.addItem(userId, productId, quantity));
    }

    @DeleteMapping("/{userId}/items/{productId}")
    public ResponseEntity<Cart> removeItem(@PathVariable Long userId, @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeItem(userId, productId));
    }

    @PatchMapping("/{userId}/items/{productId}")
    public ResponseEntity<Cart> updateItemQuantity(@PathVariable Long userId, @PathVariable Long productId, @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.updateItemQuantity(userId, productId, quantity));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
