package com.vn.repository;

import com.vn.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("p1", "sản phẩm 1", 50000, "b1.jpg"));
        products.add(new Product("p2", "sản phẩm 2", 10000, "b2.jpg"));
        products.add(new Product("p3", "sản phẩm 3", 20000, "b3.jpg"));
        products.add(new Product("p4", "sản phẩm 4", 25000, "b4.jpg"));
        products.add(new Product("p5", "sản phẩm 5", 25000, "b5.jpg"));
        return products;
    }
}
