package com.example.beprojectweb.repository;

import com.example.beprojectweb.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsCategoriesByName(String name);
}
