package com.vn.filter;

import com.vn.constant.vo.AdminRole;
import com.vn.model.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "RoleFilter", urlPatterns = {
        "/admin/product/*",
        "/admin/category/*",
        "/admin/customer/*",
        "/admin/order/*",
        "/admin/register/*"
})
public class RoleFilter implements Filter {

    private final List<String> urlAdd = Arrays.asList(
            "/admin/product/add",
            "/admin/customer/add",
            "/admin/category/add",
            "/admin/order/add",
            "/admin/register"
    );

    private final List<String> urlDelete = Arrays.asList(
            "/admin/product/delete",
            "/admin/customer/delete",
            "/admin/category/delete",
            "/admin/order/delete"
    );

    private final List<String> urlUpdate = Arrays.asList(
            "/admin/product/update",
            "/admin/customer/update",
            "/admin/category/update",
            "/admin/order/update"
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        boolean rs = true;
        try {
            if (urlAdd.contains(request.getRequestURI())) {
                for (AdminRole item : admin.getRole()) {
                    if (item.name().equalsIgnoreCase(AdminRole.FULL + "") ||
                            item.name().equalsIgnoreCase(AdminRole.CREATE + "")
                    ) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
                rs = false;
            } else if (urlUpdate.contains(request.getRequestURI())) {
                for (AdminRole item : admin.getRole()) {
                    if (item.name().equalsIgnoreCase(AdminRole.FULL + "") ||
                            item.name().equalsIgnoreCase(AdminRole.UPDATE + "")
                    ) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
                rs = false;
            } else if (urlDelete.contains(request.getRequestURI())) {
                for (AdminRole item : admin.getRole()) {
                    if (item.name().equalsIgnoreCase(AdminRole.FULL + "") ||
                            item.name().equalsIgnoreCase(AdminRole.DELETE + "")
                    ) {
                        chain.doFilter(request, response);
                        return;
                    }
                }
                rs = false;
            }
            if (!rs) {
                resp.setCharacterEncoding("UTF-8");
                response.getWriter().println("<html><body> <p>Khong co quyen</p> </body></html>");
                return;
            }
        } catch (Exception e) {
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
