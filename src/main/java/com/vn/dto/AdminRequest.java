package com.vn.dto;

import com.vn.constant.vo.AdminRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRequest {

    private long customerId;

    private String username;

    private String password;

    private String rePassword;

    private AdminRole[] roles;
}
