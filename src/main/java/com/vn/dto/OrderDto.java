package com.vn.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderDto {

    private long orderId;

    private long customerId;

    private Date createDate;

    private boolean status;

    private List<OrderDetailDto> orderDetailDtoList;
}
