package com.backend.ecommerce.repository;

import com.backend.ecommerce.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.findByName;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
}
