package com.example.beprojectweb.dto.response.order;

import com.example.beprojectweb.dto.response.cart.CartItemResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
     Long id;
     BigDecimal totalPrice;
     String status;
     String paymentMethod;
     LocalDateTime createdAt;
     LocalDateTime paymentAt;

     List<OrderItemResponse> orderItems;
}
