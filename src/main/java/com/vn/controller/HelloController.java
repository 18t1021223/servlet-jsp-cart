package com.vn.controller;

import com.vn.service.CategoryService;
import com.vn.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "helloServlet", value = {"/product/*", "/home"})
public class HelloController extends HttpServlet {
    private ProductService productService;
    private CategoryService categoryService;

    public void init() {
        productService = new ProductService();
        categoryService = new CategoryService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("cart", session.getAttribute("cart"));
        session.setMaxInactiveInterval(60 * 60 * 24 * 7);
        if (request.getRequestURI().startsWith("/product/category") || request.getRequestURI().startsWith("/home")) {
            String categoryId = request.getParameter("categoryId");
            request.setAttribute("products",
                    categoryId == null ? productService.findAll() : productService.findByCategoryId(categoryId)
            );
            request.setAttribute("categories", categoryService.findAll());
            request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
        } else if (request.getRequestURI().startsWith("/product")) {
            request.setAttribute("product", productService.findById(request.getParameter("id")));
            request.getRequestDispatcher("/WEB-INF/views/product_detail.jsp").forward(request, response);
        }
    }
}