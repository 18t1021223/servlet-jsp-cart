package com.vn.repository;

import com.vn.Utils;
import com.vn.model.Product;

import java.sql.*;
import java.util.List;

public class ProductRepository {

    public List<Product> findAll(int total) {
        String sql = " select top " + total + " * from sach ";
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

    public List<Product> findByCategoryId(String id, int total) {
        String sql = " select top " + total + " * from sach where maloai = '" + id + "'";
        try {
            Statement statement = ConnectSQL.getInstance().createStatement();
            return Utils.productMappers(statement.executeQuery(sql));
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean insertProduct(Product product) {
        String sql = "insert into sach values(?,?,?,?,?,?,?,?,?)";
        Connection connection = ConnectSQL.getInstance();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getProductId());
            statement.setString(2, product.getName());
            statement.setLong(3, product.getQuantity());
            statement.setLong(4, product.getPrice());
            statement.setString(5, product.getCategoryId());
            statement.setString(6, product.getNumberChapter() + "");
            statement.setString(7, product.getImage());
            statement.setDate(8, new Date(product.getCreateDate().getTime()));
            statement.setString(9, product.getAuthor());
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

    public boolean updateProduct(Product product) {
        String sql = "update sach set tensach = ?, soluong = ?, gia = ?, maloai = ?, sotap = ?, anh = ?, tacgia = ? where masach = ?";
        Connection connection = ConnectSQL.getInstance();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(8, product.getProductId());
            statement.setString(1, product.getName());
            statement.setLong(2, product.getQuantity());
            statement.setLong(3, product.getPrice());
            statement.setString(4, product.getCategoryId());
            statement.setString(5, product.getNumberChapter() + "");
            statement.setString(6, product.getImage());
            statement.setString(7, product.getAuthor());
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

    public void deleteProduct(String id) {
        String sql = "delete from sach where masach = ?";
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
