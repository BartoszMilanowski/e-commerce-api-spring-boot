package com.bartmilan.e_comm_api.service;

import com.bartmilan.e_comm_api.dto.ProductResponseDto;
import com.bartmilan.e_comm_api.exception.ResourceNotFoundException;
import com.bartmilan.e_comm_api.model.AvailabilityStatus;
import com.bartmilan.e_comm_api.model.Category;
import com.bartmilan.e_comm_api.model.Product;
import com.bartmilan.e_comm_api.repository.CategoryRepository;
import com.bartmilan.e_comm_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public ProductResponseDto toDto(Product p) {
        return new ProductResponseDto(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getAvailable(),
                p.getPrice(),
                p.getStock(),
                p.getMainPhoto(),
                p.getGallery(),
                p.getCategory() != null ? p.getCategory().getId() : null,
                p.getCategory() != null ? p.getCategory().getName() : null
        );
    }

    public List<ProductResponseDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponseDto> getByName(String name) {
        return productRepository.findByName(name)
                .map(this::toDto);
    }

    public Product getProductEntity(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }

    public ProductResponseDto getById(Long id) {
        return productRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }

    public List<ProductResponseDto> getByCategory(String categoryName) {
        Category category = categoryService.getByName(categoryName)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryName));
        return productRepository.findByCategory(category)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDto> getByPriceBetween(BigDecimal priceMin, BigDecimal priceMax) {
        return productRepository.findByPriceBetween(priceMin, priceMax)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDto> getByNameContaining(String phrase) {
        return productRepository.findByNameContainingIgnoreCase(phrase)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDto> getByAvailability(AvailabilityStatus status) {
        return productRepository.findByAvailable(status)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Product create(Product product) {
        return productRepository.save(product)
                ;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product update(Long id, Product updated) {
        Product current = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
        current.setName(updated.getName());
        current.setDescription(updated.getDescription());
        current.setAvailable(updated.getAvailable());
        current.setPrice(updated.getPrice());
        current.setStock(updated.getStock());
        current.setMainPhoto(updated.getMainPhoto());
        current.setGallery(updated.getGallery());
        current.setCategory(updated.getCategory());
        return productRepository.save(current);
    }

}
