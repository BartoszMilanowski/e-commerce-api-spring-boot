package com.bartmilan.e_comm_api.controller;

import com.bartmilan.e_comm_api.dto.ProductResponseDto;
import com.bartmilan.e_comm_api.model.AvailabilityStatus;
import com.bartmilan.e_comm_api.model.Category;
import com.bartmilan.e_comm_api.model.Product;
import com.bartmilan.e_comm_api.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable Long id){
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductResponseDto> getByName(@PathVariable String name){
        return productService.getByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<ProductResponseDto>> getByCategory(@PathVariable String categoryName){
        return ResponseEntity.ok(productService.getByCategory(categoryName));
    }

    @GetMapping("/price")
    public ResponseEntity<List<ProductResponseDto>> getByPriceBetween(
            @RequestParam BigDecimal priceMin,
            @RequestParam BigDecimal priceMax){
        return ResponseEntity.ok(productService.getByPriceBetween(priceMin, priceMax));
    }

    @GetMapping("/name-part/{phrase}")
    public ResponseEntity<List<ProductResponseDto>> getByNameContaining (@PathVariable String phrase){
        return ResponseEntity.ok(productService.getByNameContaining(phrase));
    }

    @GetMapping("/availability")
    public ResponseEntity<List<ProductResponseDto>> getByAvailabilityStatus(@RequestParam AvailabilityStatus status){
        return ResponseEntity.ok(productService.getByAvailability(status));
    }


    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.toDto(productService.create(product)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id, @RequestBody Product product){
        return ResponseEntity.ok(productService.toDto(productService.update(id, product)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
