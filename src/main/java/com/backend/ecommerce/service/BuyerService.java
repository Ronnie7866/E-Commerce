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

    public void delete(Buyer buyer) {
        buyerRepository.delete(buyer);
    }

    public Buyer updateBuyer(Buyer buyer, int id) {
        Buyer oldBuyer = findById(id);
        buyer.setId(oldBuyer.getId());
        buyer.setAddress(oldBuyer.getAddress());
        buyer.setEmail(oldBuyer.getEmail());
        buyer.setDefaultPhone(oldBuyer.getDefaultPhone());
        buyer.setFirstName(oldBuyer.getFirstName());
        buyer.setLastName(oldBuyer.getLastName());
        return buyerRepository.save(buyer);
    }
}


