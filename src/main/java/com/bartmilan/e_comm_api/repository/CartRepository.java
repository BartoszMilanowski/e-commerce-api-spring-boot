package com.bartmilan.e_comm_api.repository;

import com.bartmilan.e_comm_api.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long id);

}
