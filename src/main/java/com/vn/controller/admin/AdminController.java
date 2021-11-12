package com.vn.controller.admin;

import com.vn.constant.vo.AdminRole;
import com.vn.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vn.constant.Constant.PATH_VIEW_ADMIN;

@WebServlet(name = "AdminController", value = "/admin/*")
public class AdminController extends HttpServlet {
    private AdminService adminService;

    @Override
    public void init() throws ServletException {
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals("/admin/login")) {
            request.getRequestDispatcher(PATH_VIEW_ADMIN + "login.jsp").forward(request, response);
        } else if (request.getRequestURI().equals("/admin/register")) {
            request.setAttribute("roles", AdminRole.values());
            request.getRequestDispatcher(PATH_VIEW_ADMIN + "register.jsp").forward(request, response);
        } else if (request.getRequestURI().equals("/admin/index")) {
            request.getRequestDispatcher(PATH_VIEW_ADMIN + "index.jsp").forward(request, response);
        }else if (request.getRequestURI().equals("/admin/logout")) {
            adminService.logout(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals("/admin/login")) {
            adminService.login(request, response);
        } else if (request.getRequestURI().equals("/admin/register")) {
            adminService.register(request,response);
        }
    }
}
