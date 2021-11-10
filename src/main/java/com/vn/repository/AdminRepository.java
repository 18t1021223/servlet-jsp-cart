package com.vn.repository;

import com.vn.Utils;
import com.vn.model.Admin;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminRepository {

    public Admin findByUsername(String username) {
        String sql = "select * from dangnhap where tendangnhap = ?";
        try {
            PreparedStatement statement = ConnectSQL.getInstance().prepareStatement(sql);
            statement.setString(1, username);
            return Utils.adminMapper(statement.executeQuery());
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
