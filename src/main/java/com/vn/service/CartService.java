package com.vn.service;

import com.vn.model.CartItem;
import com.vn.model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CartService {

    private ProductService productService;
    private Map<String, CartItem> map;

    public CartService(Map<String, CartItem> map) {
        productService = new ProductService();
        if (map == null) {
            this.map = new HashMap<>();
        } else {
            this.map = map;
        }
    }

    public Map process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo().split("[/]", 3)[1];
        switch (path.toLowerCase()) {
            case "add":
                addProduct(productService.getProduct(request), 1);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "delete":
                if(request.getParameter("id") != null) {
                    Arrays.stream(request.getParameterValues("id"))
                            .forEach(this::deleteProduct);
                }
                request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
                break;

            case "edit":
                String[] ids = request.getParameterValues("id");
                String[] quantity = request.getParameterValues("quantity");
                for (int i = 0; i < ids.length; i++) {
                    editProduct(ids[i], Integer.parseInt(quantity[i]));
                }
            case "view":
                request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
                break;
        }
        return map;
    }

    public void addProduct(Product item, int quantity) {
        String productId = item.getId();
        CartItem local = map.get(productId);
        if (local != null) {
            local.setQuantity(local.getQuantity() + 1);
            local.setTotal(local.getQuantity() * local.getProduct().getPrice());
            map.put(productId, local);
        } else {
            map.put(productId, new CartItem(quantity, item, item.getPrice() * quantity));
        }
    }

    public void deleteProduct(String productId) {
        map.remove(productId);
    }

    public void editProduct(String id, int quantity) {
        CartItem local = map.get(id);
        if (local != null) {
            local.setQuantity(quantity);
            local.setTotal(local.getQuantity() * local.getProduct().getPrice());
            map.put(id, local);
        }
    }

    public long getTotalPrice() {
        return map.values().stream()
                .mapToLong(CartItem::getTotal).sum();
    }
}
