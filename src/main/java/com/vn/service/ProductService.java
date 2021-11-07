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

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product getProduct(HttpServletRequest request) {
        Product p = new Product();
        p.setProductId(request.getParameter("id"));
        p.setName(request.getParameter("name"));
        p.setImage(request.getParameter("image"));
        p.setPrice(Long.parseLong(request.getParameter("price")));
        return p;
    }
}
