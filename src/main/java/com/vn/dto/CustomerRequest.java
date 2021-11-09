package com.vn.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
    private long customerId;

    private String name;

    private String username;

    private String password;

    private String rePassword;
}
