package com.vn.repository;

import com.vn.Utils;
import com.vn.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductRepository {

    public List<Product> findAll() {
        String sql = " select top 30 * from sach ";
        try {
            Statement statement = ConnectSQL.getInstance().createStatement();
            return Utils.productMappers(statement.executeQuery(sql));
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public Product findById(String id) {
        String sql = " select * from sach where masach = '" + id + "'";
        try {
            Statement statement = ConnectSQL.getInstance().createStatement();
            return Utils.productMapper(statement.executeQuery(sql));
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public List<Product> findByCategoryId(String id) {
        String sql = " select top 30 * from sach where maloai = '" + id + "'";
        try {
            Statement statement = ConnectSQL.getInstance().createStatement();
            return Utils.productMappers(statement.executeQuery(sql));
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean insertProduct(Product product) {
        // TODO query sql
        String sql = "insert into sach() values()";
        Connection connection = ConnectSQL.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            // TODO set para statement
            //statement.setString(1);
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
}
