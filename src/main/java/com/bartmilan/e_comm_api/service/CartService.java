package com.bartmilan.e_comm_api.service;

import com.bartmilan.e_comm_api.exception.ResourceNotFoundException;
import com.bartmilan.e_comm_api.model.Cart;
import com.bartmilan.e_comm_api.model.CartItem;
import com.bartmilan.e_comm_api.model.Product;
import com.bartmilan.e_comm_api.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(new Cart(userId)));
    }

    @Transactional
    public Cart addItem(Long userId, Long productId, Integer quantity) {
        Cart cart = getOrCreateCart(userId);

        Product product = productService.getProductEntity(productId);

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem(cart, product, quantity, product.getPrice());
            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeItem(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: " + userId));

        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart updateItemQuantity(Long userId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: " + userId));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found in cart: " + productId));

        if (quantity <= 0) {
            cart.getItems().remove(item);
        } else {
            item.setQuantity(quantity);
        }

        return cartRepository.save(cart);
    }

    @Transactional
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: " + userId));
        cartRepository.delete(cart);
    }

    public Cart getCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: " + userId));
    }

}
