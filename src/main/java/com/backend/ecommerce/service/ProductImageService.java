package com.backend.ecommerce.service;

import com.backend.ecommerce.entity.ProductImageTable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductImageService {
    ProductImageTable saveProductImage(MultipartFile file, Long productId) throws IOException;
}
