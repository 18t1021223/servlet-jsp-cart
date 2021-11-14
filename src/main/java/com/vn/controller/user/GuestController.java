package com.vn.controller.user;

import com.vn.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vn.constant.Constant.PATH_VIEW_USER;

@WebServlet(name = "GuestController", urlPatterns = {"/guest/*"})
public class GuestController extends HttpServlet {

    public CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().contains("/guest/login")) {
            req.getRequestDispatcher(PATH_VIEW_USER + "login.jsp").forward(req, resp);
        } else if (req.getRequestURI().contains("/guest/register")) {
            req.getRequestDispatcher(PATH_VIEW_USER + "register.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getRequestURI().contains("/guest/login")) {
                customerService.login(req, resp);
            } else if (req.getRequestURI().contains("/guest/register")) {
                customerService.register(req, resp);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
