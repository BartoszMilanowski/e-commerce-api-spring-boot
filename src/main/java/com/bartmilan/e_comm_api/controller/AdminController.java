package com.bartmilan.e_comm_api.controller;

import com.bartmilan.e_comm_api.dto.OrderResponseDto;
import com.bartmilan.e_comm_api.dto.ProductResponseDto;
import com.bartmilan.e_comm_api.model.Category;
import com.bartmilan.e_comm_api.model.OrderStatus;
import com.bartmilan.e_comm_api.model.Product;
import com.bartmilan.e_comm_api.service.CategoryService;
import com.bartmilan.e_comm_api.service.OrderService;
import com.bartmilan.e_comm_api.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final OrderService orderService;

    public AdminController(ProductService productService,
                           CategoryService categoryService,
                           OrderService orderService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.orderService = orderService;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.toDto(productService.create(product)));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id,
                                                            @RequestBody Product product) {
        return ResponseEntity.ok(productService.toDto(productService.update(id, product)));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.create(category));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,
                                                   @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.update(id, category));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAll()
                .stream()
                .map(orderService::toDto)
                .toList()
        );
    }

    @PatchMapping("/orders/{id}/status")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(@PathVariable Long id,
                                                              @RequestParam OrderStatus status){
        return ResponseEntity.ok(orderService.toDto(orderService.updateStatus(id, status)));
    }
}
