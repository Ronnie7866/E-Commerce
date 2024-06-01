package com.backend.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.ecommerce.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByDefaultPhoneNumber(Long aLong);

    boolean existsByEmail(String email);

    boolean existsByIdAndFirstNameAndLastNameAndDefaultPhoneNumber(Long id, String firstName, String lastName, Long defaultPhoneNumber);
}
