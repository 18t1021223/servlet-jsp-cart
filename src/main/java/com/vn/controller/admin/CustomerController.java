package com.vn.controller.admin;

import com.vn.constant.Constant;
import com.vn.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "CustomerController", urlPatterns = {"/admin/customer/*"})
public class CustomerController extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/admin/customer/update")) {
            req.setAttribute("customer", customerService.findById(Long.parseLong(req.getParameter("id"))));
        }
        req.setAttribute("customers", customerService.findAll());
        req.setAttribute("message", req.getParameter("message"));
        req.getRequestDispatcher(Constant.PATH_VIEW_ADMIN + "customer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/admin/customer/add")) {
            if (!customerService.insertCustomer(req)) {
                resp.sendRedirect("/admin/customer?message=" +
                        URLEncoder.encode(req.getAttribute("message") + "", "UTF-8")
                );
            } else {
                resp.sendRedirect("/admin/customer");
            }
        } else if (req.getRequestURI().equals("/admin/customer/delete")) {
            customerService.deleteCustomer(req);
            resp.sendRedirect("/admin/customer");
        } else if (req.getRequestURI().equals("/admin/customer/update")) {
            if (!customerService.updateCustomer(req)) {
                resp.sendRedirect("/admin/customer/update?message=" +
                        URLEncoder.encode(req.getAttribute("message") + "", "UTF-8") +
                        "&id=" + req.getParameter("customerId")
                );
            } else {
                resp.sendRedirect("/admin/customer");
            }
        }
    }

}
