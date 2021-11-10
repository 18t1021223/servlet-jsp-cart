package com.vn.service;

import com.vn.Utils;
import com.vn.dto.CustomerRequest;
import com.vn.dto.OrderDetailDto;
import com.vn.dto.OrderDto;
import com.vn.dto.ProductDto;
import com.vn.model.CartItem;
import com.vn.model.Customer;
import com.vn.model.Order;
import com.vn.model.OrderDetail;
import com.vn.repository.CustomerRepository;
import com.vn.repository.OrderDetailRepository;
import com.vn.repository.OrderRepository;
import com.vn.repository.ProductRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.modelmapper.convention.MatchingStrategies.STANDARD;

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private ModelMapper modelMapper;

    public CustomerService() {
        customerRepository = new CustomerRepository();
        orderDetailRepository = new OrderDetailRepository();
        orderRepository = new OrderRepository();
        productRepository = new ProductRepository();
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STANDARD);
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Customer customer = customerRepository.findByUsernameAndPassword(req.getParameter("username"));
        if (customer == null || !BCrypt.checkpw(req.getParameter("password"), customer.getPassword())) {
            req.setAttribute("message", "Tai khoan mat khau sai");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", customer);
        session.setMaxInactiveInterval(60 * 60 * 24);
        resp.sendRedirect("/product/category");
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerRequest customerRequest = Utils.toDto(req);
        if (customerRequest.getUsername() == null || customerRequest.getUsername().isEmpty()) {
            req.setAttribute("message", "Điền tai khoan");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }
        if (customerRequest.getPassword() == null || customerRequest.getPassword().isEmpty()) {
            req.setAttribute("message", "Điền mật khẩu");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        } else if (!customerRequest.getPassword().equals(customerRequest.getRePassword())) {
            req.setAttribute("message", "Xác thực mật khẩu thất bại");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }
        Customer customer = Utils.toModel(customerRequest);
        if (!customerRepository.insertCustomer(customer)) {
            req.setAttribute("message", "Tạo tài khoản thất bại");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }
        login(req, resp);
    }

    public void logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
    }

    public void checkout(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
            if (cart != null) {
                Order order = new Order();
                order.setOrderId(new Date().getTime());
                order.setStatus(false);
                order.setCreateDate(new Date());
                order.setCustomerId(((Customer) session.getAttribute("user")).getCustomerId());

                if (orderRepository.insertOrder(order)) {
                    cart.forEach((k, v) -> {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setOrderId(order.getOrderId());
                        orderDetail.setOrderDetailId(new Date().getTime());
                        orderDetail.setQuantity(v.quantity);
                        orderDetail.setProductId(k);
                        if (!orderDetailRepository.insertOrderDetails(orderDetail)) {
                            throw new RuntimeException();
                        }
                    });
                }
            }
            session.removeAttribute("cart");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public List<OrderDto> getOrderHistory(HttpServletRequest request) {
        List<OrderDto> response = new ArrayList<>();

        List<Order> orders = orderRepository.
                findByCustomerId(((Customer) request.getSession().getAttribute("user")).getCustomerId());
        orders.forEach(item -> {
            OrderDto orderDto = modelMapper.map(item, OrderDto.class);
            List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
            orderDetailRepository.findByOrderId(item.getOrderId())
                    .forEach(i -> {
                        ProductDto productDto = modelMapper
                                .map(productRepository.findById(i.getProductId()), ProductDto.class);
                        OrderDetailDto orderDetailDto = modelMapper.map(i, OrderDetailDto.class);
                        orderDetailDto.setProductDto(productDto);
                        orderDetailDtos.add(orderDetailDto);
                    });
            orderDto.setOrderDetailDtoList(orderDetailDtos);
            response.add(orderDto);
        });
        return response;
    }
}
