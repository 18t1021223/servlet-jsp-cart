package com.vn.service;

import com.vn.model.Admin;
import com.vn.repository.AdminRepository;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.vn.constant.Constant.PATH_VIEW_ADMIN;

public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService() {
        adminRepository = new AdminRepository();
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Admin admin = adminRepository.findByUsername(req.getParameter("username"));
        if (admin == null || !BCrypt.checkpw(req.getParameter("password"), admin.getPassword())) {
            req.setAttribute("message", "Tai khoan mat khau sai");
            req.getRequestDispatcher(PATH_VIEW_ADMIN + "login.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("admin", admin);
        session.setMaxInactiveInterval(60 * 60 * 24);
        resp.sendRedirect("/admin/index");
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("admin");
        response.sendRedirect("/admin/login");
    }
}
