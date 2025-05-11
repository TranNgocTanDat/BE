package com.example.beprojectweb.dto.response.category;

import com.example.beprojectweb.entity.Product;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    int cate_ID;
    String name;
    String description;
    List<Product> productList;
}
