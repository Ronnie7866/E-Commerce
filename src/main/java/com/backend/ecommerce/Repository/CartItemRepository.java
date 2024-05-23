package com.backend.ecommerce.Repository;

import com.backend.ecommerce.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndProductId(@Param("cart") Long cartId, @Param("productId") Long productId);

    List<CartItem> findAllByCartId(Long cartId);
}
