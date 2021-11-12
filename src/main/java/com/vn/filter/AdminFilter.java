package com.vn.filter;

import com.vn.constant.vo.AdminRole;
import com.vn.model.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        request.setAttribute("roles", AdminRole.values());
        if (request.getRequestURI().startsWith("/admin/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        if (request.getRequestURI().startsWith("/admin/register") && admin != null) {
            chain.doFilter(request, response);
            return;
        }

        if (admin == null) {
            ((HttpServletResponse) response).sendRedirect("/guest/admin/login");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
