package com.backend.ecommerce.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.ecommerce.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByDefaultPhoneNumber(Long aLong);

    boolean existsByEmail(String email);

    boolean existsByIdAndFirstNameAndLastNameAndDefaultPhoneNumber(Long id, String firstName, String lastName, Long defaultPhoneNumber);


    @Transactional
    @Query("update User u set u.password = ?2 where u.email = ?1")
    void updatePassword(String email, String password);
}
