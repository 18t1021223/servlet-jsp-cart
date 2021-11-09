package com.vn.repository;

import com.vn.Utils;
import com.vn.model.Product;

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
}
