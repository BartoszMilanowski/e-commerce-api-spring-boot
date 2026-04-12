package com.bartmilan.e_comm_api.repository;

import com.bartmilan.e_comm_api.model.Order;
import com.bartmilan.e_comm_api.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByUserIdAndStatus(Long userId, OrderStatus status);

}
