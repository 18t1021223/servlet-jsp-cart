package com.vn.controller.user;

import com.vn.model.CartItem;
import com.vn.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "CartController", value = {"/cart/*"})
public class CartController extends HttpServlet {

    private CartService cartService;

    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        cartService = new CartService((HashMap<String, CartItem>) session.getAttribute("cart"));
        session.setAttribute("cart", cartService.process(request, response));
        session.setMaxInactiveInterval(60 * 60 * 24 * 7);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
