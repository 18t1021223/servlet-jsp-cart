package com.vn.controller.admin;

import com.vn.constant.Constant;
import com.vn.service.OrderService;
import com.vn.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "OrderController", urlPatterns = {"/admin/order/*"})
public class OrderController extends HttpServlet {

    private ProductService productService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
        orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orders", orderService.findAll());
        req.setAttribute("message", req.getParameter("message"));
        req.getRequestDispatcher(Constant.PATH_VIEW_ADMIN + "order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().startsWith("/admin/order/delete")) {
            orderService.deleteOrder(req);
            resp.sendRedirect("/admin/order/");
        } else if (req.getRequestURI().startsWith("/admin/order/update")) {
            if (!orderService.updateStatus(req)) {
                resp.sendRedirect("/admin/order?message=" +
                        URLEncoder.encode(req.getAttribute("message") + "", "UTF-8")
                );
            } else {
                resp.sendRedirect("/admin/order");
            }
        }
    }
}
