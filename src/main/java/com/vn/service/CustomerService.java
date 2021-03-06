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

import static com.vn.constant.Constant.PATH_VIEW_USER;
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
        Customer customer = customerRepository.findByUsername(req.getParameter("username"));
        if (customer == null || !BCrypt.checkpw(req.getParameter("password"), customer.getPassword())) {
            req.setAttribute("message", "Tai khoan mat khau sai");
            req.getRequestDispatcher(PATH_VIEW_USER + "login.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", customer);
        session.setMaxInactiveInterval(60 * 60 * 24);
        resp.sendRedirect("/product/category");
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerRequest customerRequest = Utils.customerRequestToDto(req);
        if (customerRequest.getUsername() == null || customerRequest.getUsername().isEmpty()) {
            req.setAttribute("message", "??i???n tai khoan");
            req.getRequestDispatcher(PATH_VIEW_USER + "register.jsp").forward(req, resp);
            return;
        }
        if (customerRequest.getPassword() == null || customerRequest.getPassword().isEmpty()) {
            req.setAttribute("message", "??i???n m???t kh???u");
            req.getRequestDispatcher(PATH_VIEW_USER + "register.jsp").forward(req, resp);
            return;
        } else if (!customerRequest.getPassword().equals(customerRequest.getRePassword())) {
            req.setAttribute("message", "X??c th???c m???t kh???u th???t b???i");
            req.getRequestDispatcher(PATH_VIEW_USER + "register.jsp").forward(req, resp);
            return;
        }
        Customer customer = modelMapper.map(customerRequest, Customer.class);
        customer.setPassword(BCrypt.hashpw(customerRequest.getPassword(), BCrypt.gensalt()));
        if (!customerRepository.insertCustomer(customer)) {
            req.setAttribute("message", "T???o t??i kho???n th???t b???i");
            req.getRequestDispatcher(PATH_VIEW_USER + "register.jsp").forward(req, resp);
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

    public Customer findById(long id) {
        return customerRepository.findById(id);
    }


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public boolean insertCustomer(HttpServletRequest request) {
        try {
            CustomerRequest dto = Utils.customerRequestToDto(request);
            if (customerRepository.findByUsername(dto.getUsername()) != null) {
                request.setAttribute("message", "Kh??ch h??ng ???? t???n t???i");
                return false;
            }
            dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
            if (!customerRepository.insertCustomer(modelMapper.map(dto, Customer.class))) {
                request.setAttribute("message", "Th??m khach hang th???t b???i");
                return false;
            }
            return true;
        } catch (Exception exception) {
            request.setAttribute("message", "Th??m khach hang th???t b???i");
            return false;
        }
    }

    public void deleteCustomer(HttpServletRequest req) {
        customerRepository.deleteCustomer(Long.parseLong(req.getParameter("id")));
    }

    public boolean updateCustomer(HttpServletRequest request) {
        try {
            CustomerRequest dto = Utils.customerRequestToDto(request);
            if (customerRepository.findByUsername(dto.getUsername()) == null) {
                request.setAttribute("message", "khach hang khong ton tai");
                return false;
            }
            if (!customerRepository.updateCustomer(modelMapper.map(dto, Customer.class))) {
                request.setAttribute("message", "s???a khach hang that bai");
                return false;
            }
            return true;
        } catch (Exception exception) {
            request.setAttribute("message", "s???a khach hang that bai");
        }
        return false;
    }
}
