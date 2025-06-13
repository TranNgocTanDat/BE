package com.example.beprojectweb.controller;

import com.example.beprojectweb.dto.response.APIResponse;
import com.example.beprojectweb.dto.response.cart.CartResponse;
import com.example.beprojectweb.entity.Cart;
import com.example.beprojectweb.mapper.CartMapper;
import com.example.beprojectweb.service.cart.ICartItemService;
import com.example.beprojectweb.service.cart.ICartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {

    ICartItemService cartItemService;
    ICartService cartService;
    CartMapper cartMapper;

    @GetMapping
    public APIResponse<CartResponse> getUserCart() {
        Cart cart = cartService.getOrCreateCartForUser();
        CartResponse response = cartMapper.toCartResponse(cart);
        return APIResponse.<CartResponse>builder()
                .result(response)
                .build();
    }

}
