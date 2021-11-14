package com.vn.controller.admin;

import com.vn.constant.Constant;
import com.vn.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "CategoryController", urlPatterns = {"/admin/category/*"})
public class CategoryController extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init() {
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/admin/category/update")) {
            req.setAttribute("category", categoryService.findById(req.getParameter("id")));
        }
        req.setAttribute("categories", categoryService.findAll());
        req.setAttribute("message", req.getParameter("message"));
        req.getRequestDispatcher(Constant.PATH_VIEW_ADMIN + "category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/admin/category/add")) {
            if (!categoryService.insertCategory(req)) {
                resp.sendRedirect("/admin/category?message=" +
                        URLEncoder.encode(req.getAttribute("message") + "", "UTF-8")
                );
            } else {
                resp.sendRedirect("/admin/category");
            }
        } else if (req.getRequestURI().equals("/admin/category/delete")) {
            categoryService.deleteCategory(req);
            resp.sendRedirect("/admin/category");
        } else if (req.getRequestURI().equals("/admin/category/update")) {
            if (!categoryService.updateCategory(req)) {
                resp.sendRedirect("/admin/category/update?message=" +
                        URLEncoder.encode(req.getAttribute("message") + "", "UTF-8") +
                        "&id=" + req.getParameter("categoryId")
                );
            } else {
                resp.sendRedirect("/admin/category");
            }
        }
    }
}
