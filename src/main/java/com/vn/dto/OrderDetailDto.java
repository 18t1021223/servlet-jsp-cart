package com.vn.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {

    private long orderDetailId;

    private int quantity;

    private long orderId;

    private ProductDto productDto;
}
