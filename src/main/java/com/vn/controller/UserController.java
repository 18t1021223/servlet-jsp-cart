package com.vn.controller;

import com.vn.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UserController", urlPatterns = {"/user/*"})
public class UserController extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() {
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().contains("/logout")) {
            customerService.logout(req);
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        } else if (req.getRequestURI().contains("/checkout")) {
            customerService.checkout(req);
            resp.sendRedirect("/product/category");
        } else if (req.getRequestURI().contains("/order")) {
            req.setAttribute("orders", customerService.getOrderHistory(req));
            req.getRequestDispatcher("/WEB-INF/views/order.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
