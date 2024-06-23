//package com.backend.ecommerce.controllers;
//
//import com.backend.ecommerce.entity.Seller;
//import com.backend.ecommerce.service.SellerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/sellers")
//public class SellerController {
//
//    @Autowired
//    private SellerService sellerService;
//
//    @GetMapping
//    public List<Seller> getAllSellers() {
//        return sellerService.getAllSellers();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) {
//        Optional<Seller> seller = sellerService.getSellerById(id);
//        return seller.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public Seller createSeller(@RequestBody Seller seller) {
//        return sellerService.saveSeller(seller);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @RequestBody Seller updatedSeller) {
//        Seller seller = sellerService.updateSeller(id, updatedSeller);
//        if (seller != null) {
//            return ResponseEntity.ok(seller);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
//        sellerService.deleteSeller(id);
//        return ResponseEntity.noContent().build();
//    }
//}
