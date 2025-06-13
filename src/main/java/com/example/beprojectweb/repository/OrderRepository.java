package com.example.beprojectweb.repository;

import com.example.beprojectweb.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
