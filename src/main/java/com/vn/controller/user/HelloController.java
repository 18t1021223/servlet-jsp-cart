package com.vn.controller.user;

import com.vn.service.CategoryService;
import com.vn.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.vn.constant.Constant.PATH_VIEW_USER;

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
                    categoryId == null ? productService.findAll(30) : productService.findByCategoryId(categoryId, 30)
            );
            request.setAttribute("categories", categoryService.findAll());
            request.getRequestDispatcher(PATH_VIEW_USER + "home.jsp").forward(request, response);
        } else if (request.getRequestURI().startsWith("/product")) {
            request.setAttribute("product", productService.findById(request.getParameter("id")));
            request.getRequestDispatcher(PATH_VIEW_USER + "product_detail.jsp").forward(request, response);
        }
    }
}