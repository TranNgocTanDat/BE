package com.example.beprojectweb.mapper;

import com.example.beprojectweb.dto.response.cart.CartItemResponse;
import com.example.beprojectweb.dto.response.order.OrderItemResponse;
import com.example.beprojectweb.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "product.productId", target = "productId")
    @Mapping(source = "product.productName", target = "productName")
    OrderItemResponse toOrderResponse(OrderItem orderItem);
}
