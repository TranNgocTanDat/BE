package com.example.beprojectweb.entity;

import com.example.beprojectweb.enums.OrderStatus;
import com.example.beprojectweb.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    BigDecimal totalPrice;            // Tổng tiền đơn hàng

    @Enumerated(EnumType.STRING)
    OrderStatus status;               // Trạng thái (PENDING, PAID, COMPLETED, CANCELLED)

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    PaymentMethod paymentMethod;             // Hình thức thanh toán (COD, MOMO, ZaloPay...)

    LocalDateTime createdAt;          // Thời gian đặt
    LocalDateTime paymentAt;          // Thời gian thanh toán (nếu có)

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    Set<OrderItem> orderItems = new HashSet<>(); // Danh sách sản phẩm
}
