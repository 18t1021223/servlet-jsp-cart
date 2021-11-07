package com.vn.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private long orderId;

    private long customerId;

    private Date createDate;

    private boolean status;
}
