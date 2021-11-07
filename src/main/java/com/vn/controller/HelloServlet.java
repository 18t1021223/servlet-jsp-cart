package com.vn.controller;

import com.vn.service.CategoryService;
import com.vn.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/home")
public class HelloServlet extends HttpServlet {
    private ProductService productService;
    private CategoryService categoryService;
    public void init() {
        productService = new ProductService();
        categoryService = new CategoryService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("products", productService.findAll());
        request.setAttribute("categories",categoryService.findAll());
        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }

    public void destroy() {
    }
}