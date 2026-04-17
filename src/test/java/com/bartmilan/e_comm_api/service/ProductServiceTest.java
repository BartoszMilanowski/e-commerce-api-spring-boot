package com.bartmilan.e_comm_api.service;

import com.bartmilan.e_comm_api.dto.ProductResponseDto;
import com.bartmilan.e_comm_api.exception.ResourceNotFoundException;
import com.bartmilan.e_comm_api.model.AvailabilityStatus;
import com.bartmilan.e_comm_api.model.Category;
import com.bartmilan.e_comm_api.model.Product;
import com.bartmilan.e_comm_api.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private Category category;

    @BeforeEach
    void setUp(){
        category = new Category("Electronics", "Phones and laptops");
        category.setId(1L);

        product = new Product(
                "iPhone 15",
                "Apple smartphone",
                AvailabilityStatus.AVAILABLE,
                new BigDecimal("3999.99"),
                50L,
                "iphone.jpg",
                null,
                category
        );
        product.setId(1L);
    }

    @Test
    void getById_shouldReturnProduct_whenExist(){
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductResponseDto result = productService.getById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("iPhone 15");
        assertThat(result.getPrice()).isEqualByComparingTo("3999.99");
        assertThat(result.getCategoryName()).isEqualTo("Electronics");
    }

    @Test
    void getById_shouldThrowException_whenNotExist(){
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.getById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void getAll_shouldReturnAllProducts(){
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<ProductResponseDto> result = productService.getAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("iPhone 15");
    }

    @Test
    void create_shouldSaveAndReturnProduct(){
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.create(product);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("iPhone 15");
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void delete_shouldCallRepository(){
        doNothing().when(productRepository).deleteById(1L);

        productService.delete(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void update_shouldUpdateAndReturnProduct(){
        Product updated = new Product(
                "iPhone 15 Pro",
                "Apple smartphone Pro",
                AvailabilityStatus.AVAILABLE,
                new BigDecimal("4999.99"),
                30L,
                "iphone_pro.jpg",
                null,
                category
        );

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.update(1L, updated);

        assertThat(result).isNotNull();
        verify(productRepository, times(1)).save(any(Product.class));
    }
}
