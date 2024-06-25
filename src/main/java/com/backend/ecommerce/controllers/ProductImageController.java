package com.backend.ecommerce.controllers;

import com.backend.ecommerce.entity.ProductImageTable;
import com.backend.ecommerce.service.ProductImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product-images")
@AllArgsConstructor
public class ProductImageController {

    private ProductImageService productImageService;

    @PostMapping("/upload")
    public ResponseEntity<ProductImageTable> uploadProductImage(@RequestParam("file") MultipartFile file, @RequestParam("productId") Long productId) throws IOException {
        ProductImageTable productImage = productImageService.saveProductImage(file, productId);
        return ResponseEntity.ok(productImage);
    }
}
