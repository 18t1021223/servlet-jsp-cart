package com.vn.repository;

import com.vn.Utils;
import com.vn.model.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrderRepository {

    public List<Order> findByCustomerId(long customerId) {
        String sql = "select * from hoadon where makh = " + customerId;
        try {
            Statement statement = ConnectSQL.getInstance().createStatement();
            return Utils.orderMappers(statement.executeQuery(sql));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    public boolean insertOrder(Order order) {
        String sql = " insert into hoadon(mahoadon,makh,ngaymua,damua) values(?,?,?,?)";
        try {
            PreparedStatement statement = ConnectSQL.getInstance().prepareStatement(sql);
            statement.setLong(1, order.getOrderId());
            statement.setLong(2, order.getCustomerId());
            statement.setString(3, order.getCreateDate() + "");
            statement.setBoolean(4, order.isStatus());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
