package com.bartmilan.e_comm_api.service;

import com.bartmilan.e_comm_api.model.AvailabilityStatus;
import com.bartmilan.e_comm_api.model.Category;
import com.bartmilan.e_comm_api.model.Product;
import com.bartmilan.e_comm_api.repository.CategoryRepository;
import com.bartmilan.e_comm_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService){
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Optional<Product> getByName(String name){
        return productRepository.findByName(name);
    }

    public Optional<Product> getById(Long id){
        return productRepository.findById(id);
    }

    public List<Product> getByCategory(String categoryName){
        Category category = categoryService.getByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return productRepository.findByCategory(category);
    }

    public List<Product> getByPriceBetween (BigDecimal priceMin, BigDecimal priceMax){
        return productRepository.findByPriceBetween(priceMin, priceMax);
    }

    public List<Product> getByNameContaining (String phrase){
        return productRepository.findByNameContaining(phrase);
    }

    public List<Product> getByAvailability(AvailabilityStatus status){
        return productRepository.findByAvailable(status);
    }

    public Product create(Product product){
        return productRepository.save(product);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public Product update(Long id, Product updated){
        Product current = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
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
