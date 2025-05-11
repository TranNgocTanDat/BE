package com.example.beprojectweb.mapper;

import com.example.beprojectweb.dto.request.product.ProductRequest;
import com.example.beprojectweb.dto.response.product.ProductResponse;
import com.example.beprojectweb.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest request);
    ProductResponse toProductResponse(Product product);
    void updateProduct(@MappingTarget Product product, ProductRequest request);
}
