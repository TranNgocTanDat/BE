package com.example.beprojectweb.dto.response.product;

import com.example.beprojectweb.entity.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ProductResponse {
    int product_ID;
    Category category;
    String productName;
    String description;
    double price;
    int stock;
    String img;

}
