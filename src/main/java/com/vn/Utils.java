package com.vn;

import com.vn.constant.vo.AdminRole;
import com.vn.dto.AdminRequest;
import com.vn.dto.CategoryDto;
import com.vn.dto.CustomerRequest;
import com.vn.dto.ProductDto;
import com.vn.model.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    //region product
    public static List<Product> productMappers(ResultSet rs) throws SQLException {
        List<Product> list = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setProductId(rs.getString("masach"));
            product.setName(rs.getString("tensach"));
            product.setQuantity(rs.getLong("soluong"));
            product.setPrice(rs.getLong("gia"));
            product.setCategoryId(rs.getString("maloai"));
            product.setNumberChapter(rs.getInt("sotap"));
            product.setImage(rs.getString("anh"));
            product.setCreateDate(rs.getDate("ngaynhap"));
            product.setAuthor(rs.getString("tacgia"));
            list.add(product);
        }
        return list;
    }

    public static Product productMapper(ResultSet rs) throws SQLException {
        Product product = null;
        if (rs.next()) {
            product = new Product();
            product.setProductId(rs.getString("masach"));
            product.setName(rs.getString("tensach"));
            product.setQuantity(rs.getLong("soluong"));
            product.setPrice(rs.getLong("gia"));
            product.setCategoryId(rs.getString("maloai"));
            product.setNumberChapter(rs.getInt("sotap"));
            product.setImage(rs.getString("anh"));
            product.setCreateDate(rs.getDate("ngaynhap"));
            product.setAuthor(rs.getString("tacgia"));
        }
        return product;
    }

    public static ProductDto ProductRequestToDto(HttpServletRequest request) {
        ProductDto productDto = new ProductDto();
        productDto.setAuthor(request.getParameter("author"));
        productDto.setPrice(Long.parseLong(request.getParameter("price")));
        productDto.setName(request.getParameter("name"));
        productDto.setImage(request.getParameter("image"));
        productDto.setNumberChapter(Integer.parseInt(request.getParameter("chapter")));
        productDto.setCategoryId(request.getParameter("categoryId"));
        productDto.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        return productDto;
    }
    //endregion

    //region category
    public static List<Category> categoryMappers(ResultSet rs) throws SQLException {
        List<Category> list = new ArrayList<>();
        while (rs.next()) {
            Category category = new Category();
            category.setCategoryId(rs.getString("maloai"));
            category.setName(rs.getString("tenloai"));
            list.add(category);
        }
        return list;
    }

    public static CategoryDto categoryRequestToDto(HttpServletRequest request) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(request.getParameter("categoryId"));
        categoryDto.setName(request.getParameter("name"));
        return categoryDto;
    }
    //endregion

    //region admin
    public static Admin adminMapper(ResultSet rs) throws SQLException {
        Admin admin = null;
        if (rs.next()) {
            admin = new Admin();
            admin.setUsername(rs.getString("tendangnhap"));
            admin.setPassword(rs.getString("matkhau"));
            admin.setRole(Arrays.stream(rs.getString("quyen").split("[;]"))
                    .map(AdminRole::valueOf)
                    .toArray(AdminRole[]::new));
        }
        return admin;
    }
    //endregion

    //region customer
    public static Customer customerMapper(ResultSet rs) throws SQLException {
        Customer customer = null;
        if (rs.next()) {
            customer = new Customer();
            customer.setUsername(rs.getString("tendn"));
            customer.setPassword(rs.getString("pass"));
            customer.setCustomerId(rs.getLong("makh"));
            customer.setName(rs.getString("hoten"));
        }
        return customer;
    }

    public static CustomerRequest customerRequestToDto(HttpServletRequest request) {
        CustomerRequest customer = new CustomerRequest();
        customer.setPassword(request.getParameter("password"));
        customer.setRePassword(request.getParameter("rePassword"));
        customer.setUsername(request.getParameter("username"));
        customer.setName(request.getParameter("name"));
        return customer;
    }

    public static Customer DtoToModel(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setPassword(BCrypt.hashpw(customerRequest.getPassword(), BCrypt.gensalt()));
        customer.setUsername(customerRequest.getUsername());
        customer.setName(customerRequest.getName());
        customer.setCustomerId(new Date().getTime());
        return customer;
    }
    //endregion

    //region order detail
    public static List<OrderDetail> orderDetailMappers(ResultSet rs) throws SQLException {
        List<OrderDetail> list = new ArrayList<>();
        while (rs.next()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderDetailId(rs.getLong("machitietHD"));
            orderDetail.setProductId(rs.getString("masach"));
            orderDetail.setQuantity(rs.getInt("soluongmua"));
            orderDetail.setOrderId(rs.getLong("mahoadon"));
            list.add(orderDetail);
        }
        return list;
    }
    //endregion

    //region order
    public static List<Order> orderMappers(ResultSet rs) throws SQLException {
        List<Order> list = new ArrayList<>();
        while (rs.next()) {
            Order order = new Order();
            order.setOrderId(rs.getLong("mahoadon"));
            order.setCustomerId(rs.getLong("makh"));
            order.setCreateDate(rs.getDate("ngaymua"));
            order.setStatus(rs.getBoolean("damua"));
            list.add(order);
        }
        return list;
    }
    //endregion

    //region admin
    public static AdminRequest adminRequestToDto(HttpServletRequest request) {
        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setPassword(request.getParameter("password"));
        adminRequest.setRePassword(request.getParameter("rePassword"));
        adminRequest.setUsername(request.getParameter("username"));
        if (request.getParameterValues("role") != null) {
            adminRequest.setRoles(Arrays.stream(request.getParameterValues("role"))
                    .map(i -> AdminRole.valueOf(i.toUpperCase()))
                    .toArray(AdminRole[]::new));
        }
        return adminRequest;
    }

    public static Admin DtoToModel(AdminRequest adminRequest) {
        Admin admin = new Admin();
        admin.setPassword(BCrypt.hashpw(adminRequest.getPassword(), BCrypt.gensalt()));
        admin.setUsername(adminRequest.getUsername());
        admin.setRole(adminRequest.getRoles());
        return admin;
    }
    //endregion
}
