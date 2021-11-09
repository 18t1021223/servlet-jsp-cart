package com.vn.dto;

import com.vn.model.Order;
import com.vn.model.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {

    private Order order;

    private List<OrderDetail> orderDetails;
}
