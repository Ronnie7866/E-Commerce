package com.backend.ecommerce.implementation;

import com.backend.ecommerce.entity.Product;
import com.backend.ecommerce.entity.ProductImageTable;
import com.backend.ecommerce.repository.ProductImageRepository;
import com.backend.ecommerce.repository.ProductRepository;
import com.backend.ecommerce.service.FileService;
import com.backend.ecommerce.service.ProductImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;
    private final FileService fileService;
    private final String uploadDir = "uploads/";

    @Override
    public ProductImageTable saveProductImage(MultipartFile file, Long productId) throws IOException {
        String fileName = fileService.uploadFile(uploadDir, file);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found "));

        ProductImageTable productImage = new ProductImageTable();
        productImage.setProduct(product);
        productImage.setImagePath(uploadDir + fileName);

        return productImageRepository.save(productImage);
    }
}
