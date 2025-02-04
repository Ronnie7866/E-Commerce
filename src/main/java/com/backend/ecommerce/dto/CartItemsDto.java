package com.backend.ecommerce.dto;

import com.backend.ecommerce.Entity.CartItem;
import com.backend.ecommerce.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemsDto {

    Long cartItemId;
    Long productId;
    String productName;
    Integer quantity;
    BigDecimal unitPrice;
    BigDecimal totalPrice;
    String imageUri;
    Boolean availability;
    LocalDateTime addedOn;
    String productApiEndpoint;

    public CartItemsDto(CartItem ci){
        Product p = ci.getProduct();
        this.cartItemId = ci.getId();
        this.productId = p.getId();
        this.productName = p.getName();
        this.unitPrice = p.getPrice();
        this.totalPrice = p.getPrice().multiply(BigDecimal.valueOf(ci.getQuantity()));
        this.quantity = ci.getQuantity();
        this.imageUri = null;
        this.availability = null; // TODO p.getAvailability();
        this.addedOn = null;
    }

}
