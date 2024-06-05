package com.backend.ecommerce.repository;

import com.backend.ecommerce.entity.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartProducts, Long> {

    Optional<CartProducts> findByCartIdAndProductId(@Param("cart") Long cartId, @Param("productId") String productId);

    List<CartProducts> findAllByCartId(Long cartId);
}
