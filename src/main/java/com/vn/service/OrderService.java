package com.vn.service;

import com.vn.dto.OrderDetailDto;
import com.vn.dto.OrderDto;
import com.vn.dto.ProductDto;
import com.vn.model.Order;
import com.vn.repository.OrderDetailRepository;
import com.vn.repository.OrderRepository;
import com.vn.repository.ProductRepository;
import org.modelmapper.ModelMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;
    private ModelMapper modelMapper;
    private final OrderDetailRepository orderDetailRepository;
    private ProductRepository productRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
        orderDetailRepository = new OrderDetailRepository();
        productRepository = new ProductRepository();
        modelMapper = new ModelMapper();
    }

    public List<OrderDto> findAll() {
        List<OrderDto> response = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
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

    public boolean updateStatus(HttpServletRequest request) {
        try {
            Order order = orderRepository.findById(Long.parseLong(request.getParameter("id")));
            if (order == null) {
                request.setAttribute("message", "hóa đơn không tồn tại");
                return false;
            }
            order.setStatus(true);
            if (!orderRepository.updateOrder(order)) {
                request.setAttribute("message", "cập nhật hóa đơn thất bại");
                return false;
            }
            return true;
        } catch (Exception e) {
            request.setAttribute("message", "cập nhật hóa đơn thất bại");
        }
        return false;
    }

    public void deleteOrder(HttpServletRequest req) {
        orderRepository.deleteOrder(Long.parseLong(req.getParameter("orderId")));
    }
}
