package com.example.beprojectweb.service.order;

import com.example.beprojectweb.dto.response.order.OrderResponse;
import com.example.beprojectweb.dto.response.order.PaymentMethodResponse;
import com.example.beprojectweb.enums.PaymentMethod;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder();
    List<OrderResponse> getAllOrder();
    PaymentMethodResponse choosePaymentMethod(Long orderId, PaymentMethod method);
    OrderResponse confirmPayment(Long orderId);
}
