package com.bartmilan.e_comm_api.repository;

import com.bartmilan.e_comm_api.model.AvailabilityStatus;
import com.bartmilan.e_comm_api.model.Category;
import com.bartmilan.e_comm_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
    List<Product> findByCategory (Category category);
    List<Product> findByPriceBetween (BigDecimal priceMin, BigDecimal priceMax);
    List<Product> findByNameContaining(String phrase);
    List<Product> findByAvailable(AvailabilityStatus status);


}
