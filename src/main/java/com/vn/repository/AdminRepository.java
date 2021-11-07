package com.vn.repository;

import com.vn.Utils;
import com.vn.model.Admin;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminRepository {

    public Admin findByUsernameAndPassword(String username, String password) {
        String sql = "select * from dangnhap where tendangnhap = ? and matkhau = ? ";
        try {
            PreparedStatement statement = ConnectSQL.getInstance().prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            return Utils.adminMapper(statement.executeQuery());
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
