package com.example.beprojectweb.controller;

import com.example.beprojectweb.dto.request.order.PaymentMethodRequest;
import com.example.beprojectweb.dto.response.order.OrderResponse;
import com.example.beprojectweb.service.order.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    /**
     * Đặt đơn hàng mới từ giỏ hàng
     */
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder() {
        OrderResponse response = orderService.createOrder();
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{orderId}/payment-method")
    public ResponseEntity<?> choosePaymentMethod(
            @PathVariable Long orderId,
            @RequestBody PaymentMethodRequest request) {
        return ResponseEntity.ok(orderService.choosePaymentMethod(orderId, request.getPaymentMethod()));
    }

    @PostMapping("/{orderId}/confirm-payment")
    public ResponseEntity<?> confirmPayment(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.confirmPayment(orderId));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getMyOrders() {
        List<OrderResponse> orders = orderService.getAllOrder();
        return ResponseEntity.ok(orders);
    }
}
