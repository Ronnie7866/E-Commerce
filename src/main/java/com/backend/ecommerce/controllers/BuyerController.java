//package com.backend.ecommerce.controllers;
//
//
//import com.backend.ecommerce.entity.Buyer;
//import com.backend.ecommerce.service.BuyerService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/buyer")
//@AllArgsConstructor
//public class BuyerController {
//
//    private final BuyerService buyerService;
//
//
//    @PostMapping
//    public ResponseEntity<Buyer> createBuyer(@RequestBody Buyer buyer) {
//        Buyer savedBuyer = buyerService.save(buyer);
//        return ResponseEntity.ok(savedBuyer);
//    }
//
//    @PostMapping("/{id}")
//    public ResponseEntity<Buyer> updateBuyer(@RequestBody Buyer buyer, @PathVariable int id) {
//        Buyer updatedBuyer = buyerService.updateBuyer(buyer, id);
//        return ResponseEntity.ok(updatedBuyer);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Buyer> getBuyerById(@PathVariable int id) {
//        Buyer buyerById = buyerService.findById(id);
//        return ResponseEntity.ok(buyerById);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteBuyer(@PathVariable int id) {
//        buyerService.delete(id);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Buyer>> getAllBuyers() {
//        List<Buyer> allBuyers = buyerService.findAll();
//        return ResponseEntity.ok(allBuyers);
//    }
//}
