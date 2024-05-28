package com.backend.ecommerce.Repository;

import com.backend.ecommerce.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    public Optional<Address> findByUserId(Long user_id);
}
