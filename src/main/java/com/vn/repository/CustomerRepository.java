package com.vn.repository;

import com.vn.Utils;
import com.vn.model.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRepository {

    public Customer findByUsernameAndPassword(String username) {
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
}
