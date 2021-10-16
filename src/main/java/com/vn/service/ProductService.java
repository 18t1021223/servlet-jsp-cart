package com.vn.service;

import com.vn.model.Product;
import com.vn.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Product getProduct(HttpServletRequest request) {
        Product p = new Product();
        p.setId(request.getParameter("id"));
        p.setName(request.getParameter("name"));
        p.setImage(request.getParameter("image"));
        p.setPrice(Long.parseLong(request.getParameter("price")));
        return p;
    }
}
