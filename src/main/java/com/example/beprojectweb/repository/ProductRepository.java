package com.example.beprojectweb.repository;


import com.example.beprojectweb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsProductByProductName(String productName);

    Optional<Product> findByProductName(String productName);

}
