package com.vn.model;

import com.vn.constant.vo.AdminRole;
import lombok.Data;

@Data
public class Admin {
    private String username;
    private String password;
    private AdminRole[] role;
}
