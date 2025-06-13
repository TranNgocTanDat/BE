package com.example.beprojectweb.service.cart;

import com.example.beprojectweb.dto.request.cart.CartItemRequest;
import com.example.beprojectweb.dto.response.cart.CartItemResponse;
import com.example.beprojectweb.entity.Cart;
import com.example.beprojectweb.entity.CartItem;
import com.example.beprojectweb.entity.Product;
import com.example.beprojectweb.entity.User;
import com.example.beprojectweb.mapper.CartItemMapper;
import com.example.beprojectweb.repository.CartItemRepository;
import com.example.beprojectweb.repository.CartRepository;
import com.example.beprojectweb.repository.ProductRepository;
import com.example.beprojectweb.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartItemService implements ICartItemService {
    UserRepository userRepository;
    CartItemRepository cartItemRepository;
    CartRepository cartRepository;
    ProductRepository productRepository;
    CartItemMapper cartItemMapper;
    CartService cartService;

    @Override
    public CartItemResponse addProductToCart(CartItemRequest request) {
        // Lấy user hiện tại
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Lấy cart cho user
        Cart cart = cartService.getOrCreateCartForUser();

        // Lấy product cần thêm
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Kiểm tra nếu game đã có trong cart thì trả luôn response cũ
        Optional<CartItem> existingItemOpt = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingItemOpt.isPresent()) {
            // Nếu game đã tồn tại trong giỏ thì không thêm nữa
            return cartItemMapper.toCartItemResponse(existingItemOpt.get());
        }

        BigDecimal price = product.getPrice();
        System.out.println(price);
        if (price == null) {
            throw new RuntimeException("Product price is null for productId: " + product.getProductId());
        }

        // Nếu chưa có thì tạo mới CartItem
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .totalPrice(product.getPrice())
                .build();

        CartItem saved = cartItemRepository.save(cartItem);
        System.out.println( "totalPrice: " + saved.getTotalPrice());

        cartService.updateCartTotalAmount(cart);

        CartItemResponse response = cartItemMapper.toCartItemResponse(saved);
        System.out.println(" totalPrice: " + response.getTotalPrice());

        return response;
    }

    @Override
    public void removeItemFromCart(long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        Cart cart = cartItem.getCart();

        cartItemRepository.delete(cartItem);
        cartService.updateCartTotalAmount(cart);
    }
}
