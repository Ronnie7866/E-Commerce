package com.backend.ecommerce.implementation;

import com.backend.ecommerce.records.ProductResponse;
import com.backend.ecommerce.entity.Category;
import com.backend.ecommerce.entity.Product;
import com.backend.ecommerce.exception.CategoryNotFoundException;
import com.backend.ecommerce.exception.ProductNotFoundException;
import com.backend.ecommerce.repository.CategoryRepository;
import com.backend.ecommerce.repository.ProductRepository;
import com.backend.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> addAll(List<Product> products) {
        List<Product> productList = new ArrayList<>();
        for (Product i : products) {
            Product product = createProduct(i);
            productList.add(product);
        }
        return productRepository.saveAll(productList);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found " + id));
        existingProduct.setId(product.getId());
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        return productRepository.save(existingProduct);
    }

    @Override
    public List<Product> getProductByCategory(Long catId) {
        Category category = categoryRepository.findById(catId).orElseThrow(() -> new CategoryNotFoundException("Category not found with this Id : " + catId));
        return new ArrayList<>(category.getProducts());
    }

    @Override
    public Product assignCategoryToProduct(Long productId, Long categoryId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        Long id = category.getId();
//        product.getCategoryIds().add(id);// TODO fix this function
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        productRepository.deleteById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException("Product not found");
    }

    @Override
    public ProductResponse getAllProductWithPagination(Integer pageNo, Integer pageSize) {
        Pageable pageable = (Pageable) PageRequest.of(pageNo, pageSize);
        Page<Product> productPage = productRepository.findAll((PageRequest) pageable);
        List<Product> productList = productPage.getContent();
        return new ProductResponse(productList, pageNo, pageSize, productPage.getTotalElements(), productPage.getTotalPages(), productPage.isLast());
    }

    @Override
    public ProductResponse getProductWithPaginationAndSorting(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = (Pageable) PageRequest.of(pageNo, pageSize, sort);
        Page<Product> productPage = productRepository.findAll((PageRequest) pageable);
        List<Product> productList = productPage.getContent();
        return new ProductResponse(productList, pageNo, pageSize, productPage.getTotalElements(), productPage.getTotalPages(), productPage.isLast());
    }
}
