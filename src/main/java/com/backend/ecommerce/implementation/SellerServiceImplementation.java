//package com.backend.ecommerce.implementation;
//
//import com.backend.ecommerce.entity.Seller;
//import com.backend.ecommerce.exception.ResourceNotFoundException;
//import com.backend.ecommerce.repository.SellerRepository;
//import com.backend.ecommerce.service.SellerService;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@NoArgsConstructor
//public class SellerServiceImplementation implements SellerService {
//
//
//    private SellerRepository sellerRepository;
//
//    public List<Seller> getAllSellers() {
//        return sellerRepository.findAll();
//    }
//
//    public Optional<Seller> getSellerById(Long id) {
//        return sellerRepository.findById(id);
//    }
//
//    public Seller saveSeller(Seller seller) {
//        return sellerRepository.save(seller);
//    }
//
//    public Seller updateSeller(Long id, Seller updatedSeller) {
//        Optional<Seller> existingSeller = sellerRepository.findById(id);
//        if (existingSeller.isPresent()) {
//            Seller seller = existingSeller.get();
//            seller.setFirstName(updatedSeller.getFirstName());
//            seller.setLastName(updatedSeller.getLastName());
//            seller.setEmail(updatedSeller.getEmail());
//            seller.setPassword(updatedSeller.getPassword());
//            seller.setCompanyName(updatedSeller.getCompanyName());
//            seller.setGstNumber(updatedSeller.getGstNumber());
//            return sellerRepository.save(seller);
//        }
//        return null;
//    }
//
//    public void deleteSeller(Long id) {
//        sellerRepository.deleteById(id);
//    }
//}
//
