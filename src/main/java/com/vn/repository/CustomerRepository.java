package com.vn.repository;

import com.vn.Utils;
import com.vn.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CustomerRepository {

    public Customer findByUsername(String username) {
        String sql = "select * from khachhang where tendn = ?";
        try {
            PreparedStatement statement = ConnectSQL.getInstance().prepareStatement(sql);
            statement.setString(1, username);
            return Utils.customerMapper(statement.executeQuery());
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean insertCustomer(Customer customer) {
        String sql = " insert into khachhang(makh,hoten,tendn,pass) values(?,?,?,?)";
        try {
            PreparedStatement statement = ConnectSQL.getInstance().prepareStatement(sql);
            statement.setLong(1, customer.getCustomerId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getUsername());
            statement.setString(4, customer.getPassword());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public Customer findById(long id) {
        String sql = "select * from khachhang where makh = ?";
        try {
            PreparedStatement statement = ConnectSQL.getInstance().prepareStatement(sql);
            statement.setLong(1, id);
            return Utils.customerMapper(statement.executeQuery());
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean updateCustomer(Customer customer) {
        String sql = "update khachhang set hoten = ?, tendn = ?, pass = ? where makh = ?";
        Connection connection = ConnectSQL.getInstance();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getUsername());
            statement.setString(3, customer.getPassword());
            statement.setLong(4, customer.getCustomerId());
            if (statement.executeUpdate() == 1) {
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

    public boolean deleteCustomer(long id) {
        String sql = "delete from khachhang where makh = ?";
        Connection connection = ConnectSQL.getInstance();
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            if (statement.executeUpdate() == 1) {
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

    public List<Customer> findAll() {
        String sql = "select * from khachhang";
        try {
            Statement statement = ConnectSQL.getInstance().createStatement();
            return Utils.customerMappers(statement.executeQuery(sql));
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
