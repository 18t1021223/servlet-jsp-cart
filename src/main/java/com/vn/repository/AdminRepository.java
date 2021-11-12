package com.vn.repository;

import com.vn.Utils;
import com.vn.constant.vo.AdminRole;
import com.vn.model.Admin;

import java.sql.Connection;
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

    public boolean insertAdmin(Admin admin) {
        String sql = " insert into dangnhap(tendangnhap,matkhau,quyen) values(?,?,?)";
        Connection connection = ConnectSQL.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, admin.getUsername());
            statement.setString(2, admin.getPassword());
            StringBuilder builder = new StringBuilder();
            for (AdminRole adminRole : admin.getRole()) {
                builder.append(adminRole.name()).append(";");
            }
            builder.deleteCharAt(builder.lastIndexOf(";"));
            statement.setString(3, builder.toString());
            connection.setAutoCommit(false);
            if (statement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
            connection.rollback();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectSQL.setAutocommit(connection, true);
        }
        return false;
    }

}
