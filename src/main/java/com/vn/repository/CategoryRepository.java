package com.vn.repository;

import com.vn.Utils;
import com.vn.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CategoryRepository {

    public List<Category> findAll() {
        String sql = "select * from loai";
        try {
            Statement statement = ConnectSQL.getInstance().createStatement();
            return Utils.categoryMappers(statement.executeQuery(sql));
        } catch (SQLException exception) {
            return null;
        }
    }

    public boolean insertCategory(Category category) {
        String sql = "insert into loai values(?,?)";
        Connection connection = ConnectSQL.getInstance();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getCategoryId());
            statement.setString(2, category.getName());
            if (statement.executeUpdate() == 1) {
                connection.commit();
                return true;
            }
            connection.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectSQL.setAutocommit(connection, true);
        }
        return false;
    }

    public void deleteCategory(String id) {
        String sql = "delete loai where = ?";
        Connection connection = ConnectSQL.getInstance();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            if (statement.executeUpdate() == 1) {
                connection.commit();
                return;
            }
            connection.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectSQL.setAutocommit(connection, true);
        }
    }
}
