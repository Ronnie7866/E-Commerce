package com.backend.ecommerce.service;

import com.backend.ecommerce.entity.Buyer;
import com.backend.ecommerce.repository.BuyerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BuyerService {

    private final BuyerRepository buyerRepository;

    public List<Buyer> findAll() {
        return buyerRepository.findAll();
    }

    public Buyer findById(int id) {
        return buyerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Buyer ID"));
    }

    public Buyer save(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    public void delete(int id) {
        buyerRepository.deleteById(id);
    }

    public Buyer updateBuyer(Buyer buyer, int id) {
        Buyer oldBuyer = findById(id);
        oldBuyer.setFirstName(buyer.getFirstName());
        oldBuyer.setLastName(buyer.getLastName());
        oldBuyer.setAddress(buyer.getAddress());
        oldBuyer.setEmail(buyer.getEmail());
        oldBuyer.setPassword(buyer.getPassword());
        oldBuyer.setSecurityAnswer(buyer.getSecurityAnswer());
        oldBuyer.setSecurityQuestion(buyer.getSecurityQuestion());
        oldBuyer.setCart(buyer.getCart());
        oldBuyer.setAddress(buyer.getAddress());
        oldBuyer.setUser(buyer.getUser());
        return buyerRepository.save(buyer);
    }
}


