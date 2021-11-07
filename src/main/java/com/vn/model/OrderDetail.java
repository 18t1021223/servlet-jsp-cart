package com.vn.model;

import lombok.Data;

@Data
public class OrderDetail {

    private long orderDetailId;

    private String productId;

    private int quantity;

    private long orderId;

}
