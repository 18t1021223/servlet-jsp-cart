package com.vn.controller.admin;

import com.vn.constant.Constant;
import com.vn.service.CategoryService;
import com.vn.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet(name = "ProductController", urlPatterns = {"/admin/product/*"})
public class ProductController extends HttpServlet {
    private ProductService productService;
    private CategoryService categoryService;

    @Override
    public void init() {
        productService = new ProductService();
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/admin/product/update")) {
            req.setAttribute("product", productService.findById(req.getParameter("id")));
        }
        req.setAttribute("products", productService.findAll(100));
        req.setAttribute("categories", categoryService.findAll());
        req.setAttribute("message", req.getParameter("message"));
        req.getRequestDispatcher(Constant.PATH_VIEW_ADMIN + "product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/admin/product/add")) {
            if (!productService.insertProduct(req)) {
                resp.sendRedirect("/admin/product?message=" +
                        URLEncoder.encode(req.getAttribute("message") + "", "UTF-8")
                );
            } else {
                resp.sendRedirect("/admin/product");
            }
        } else if (req.getRequestURI().equals("/admin/product/delete")) {
            productService.deleteProduct(req);
            resp.sendRedirect("/admin/product");
        } else if (req.getRequestURI().equals("/admin/product/update")) {
            if (!productService.updateProduct(req)) {
                resp.sendRedirect("/admin/product/update?message=" +
                        URLEncoder.encode(req.getAttribute("message") + "", "UTF-8") +
                        "&id=" + req.getParameter("productId")
                );
            } else {
                resp.sendRedirect("/admin/product");
            }
        }
    }
}
