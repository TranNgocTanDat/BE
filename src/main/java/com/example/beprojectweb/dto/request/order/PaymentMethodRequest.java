package com.example.beprojectweb.dto.request.order;

import com.example.beprojectweb.enums.PaymentMethod;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentMethodRequest {
    PaymentMethod paymentMethod;
}
