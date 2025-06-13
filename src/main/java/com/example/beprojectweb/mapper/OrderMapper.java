package com.example.beprojectweb.mapper;

import com.example.beprojectweb.dto.request.order.PaymentMethodRequest;
import com.example.beprojectweb.dto.response.order.OrderResponse;
import com.example.beprojectweb.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {
    Order toOrder(PaymentMethodRequest paymentMethodRequest);

    @Mapping(source = "orderItems", target = "orderItems")
    @Mapping(source = "totalPrice", target = "totalPrice")
    OrderResponse toOrderResponse(Order order);
}

