package com.example.beprojectweb.dto.response.order;

import com.example.beprojectweb.enums.PaymentMethod;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentMethodResponse {
     Long id;
     PaymentMethod method;
}
