package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"user", "orderItems", "orderItems.product"})
    @Override
    Optional<Order> findById(Long id);

    @EntityGraph(attributePaths = {"user", "orderItems"})
    List<Order> findByUserId(Long userId);

    @EntityGraph(attributePaths = {"user", "orderItems"})
    @Override
    List<Order> findAll();
}
