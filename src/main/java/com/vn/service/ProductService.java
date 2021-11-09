package com.vn.service;

import com.vn.model.Product;
import com.vn.repository.ProductRepository;

import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> findByCategoryId(String id) {
        return productRepository.findByCategoryId(id);
    }
}
