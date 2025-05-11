package com.example.beprojectweb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int product_ID;

    @ManyToOne
    @JoinColumn(name = "cate_ID", referencedColumnName = "cate_ID")
    @JsonBackReference
    Category category;
    String productName;
    String description;
    double price;
    int stock;
    String img;



}
