package com.example.beprojectweb.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    BigDecimal totalPrice;                 // Giá tại thời điểm mua (có thể khác giá hiện tại)

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;                      // Đơn hàng tổng

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;                        // Game cụ thể

}
