package com.backend.ecommerce.controllers;

import com.backend.ecommerce.entity.UserBankDetails;
import com.backend.ecommerce.service.UserBankDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bankdetails")
@AllArgsConstructor
public class UserBankDetailsController {

    private final UserBankDetailsService userBankDetailsService;

    @GetMapping
    public List<UserBankDetails> getAllBankDetails() {
        return userBankDetailsService.getAllBankDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBankDetails> getBankDetailsById(@PathVariable int id) {
        Optional<UserBankDetails> bankDetails = userBankDetailsService.getBankDetailsById(id);
        return bankDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<UserBankDetails> getBankDetailsByUserId(@PathVariable Long userId) {
        return userBankDetailsService.getBankDetailsByUserId(userId);
    }

    @GetMapping("/primary/{isPrimary}")
    public List<UserBankDetails> getPrimaryBankDetails(@PathVariable boolean isPrimary) {
        return userBankDetailsService.getPrimaryBankDetails(isPrimary);
    }

    @GetMapping("/status/{status}")
    public List<UserBankDetails> getBankDetailsByStatus(@PathVariable String status) {
        return userBankDetailsService.getBankDetailsByStatus(status);
    }

    @PostMapping
    public UserBankDetails saveBankDetails(@RequestBody UserBankDetails bankDetails) {
        return userBankDetailsService.saveBankDetails(bankDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankDetails(@PathVariable int id) {
        userBankDetailsService.deleteBankDetails(id);
        return ResponseEntity.noContent().build();
    }
}
