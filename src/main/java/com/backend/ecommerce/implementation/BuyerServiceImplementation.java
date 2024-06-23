//package com.backend.ecommerce.service;
//
//import com.backend.ecommerce.entity.Buyer;
//import com.backend.ecommerce.repository.BuyerRepository;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@NoArgsConstructor
//public class BuyerServiceImplementation implements BuyerService {
//
//    private BuyerRepository buyerRepository;
//
//    public List<Buyer> getAllBuyers() {
//        return buyerRepository.findAll();
//    }
//
//    public Optional<Buyer> getBuyerById(Long id) {
//        return buyerRepository.findById(id);
//    }
//
//    public Buyer saveBuyer(Buyer buyer) {
//        return buyerRepository.save(buyer);
//    }
//
//    public Buyer updateBuyer(Long id, Buyer updatedBuyer) {
//        Optional<Buyer> existingBuyer = buyerRepository.findById(id);
//        if (existingBuyer.isPresent()) {
//            Buyer buyer = existingBuyer.get();
//            buyer.setFirstName(updatedBuyer.getFirstName());
//            buyer.setLastName(updatedBuyer.getLastName());
//            buyer.setEmail(updatedBuyer.getEmail());
//            buyer.setPassword(updatedBuyer.getPassword());
//            return buyerRepository.save(buyer);
//        }
//        return null;
//    }
//
//    public void deleteBuyer(Long id) {
//        buyerRepository.deleteById(id);
//    }
//}
