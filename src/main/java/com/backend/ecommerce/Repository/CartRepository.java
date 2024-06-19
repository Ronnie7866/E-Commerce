package com.backend.ecommerce.Repository;

import com.backend.ecommerce.Entity.Cart;
import com.backend.ecommerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);

    @Query(name = "DELETE FROM cart WHERE user_id = :userID", nativeQuery = true)
    boolean deleteByUserId(Long userId);

}
