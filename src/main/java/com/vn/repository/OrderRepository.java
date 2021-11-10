package com.vn.repository;

import com.vn.Utils;
import com.vn.model.Order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class OrderRepository {

    public List<Order> findByCustomerId(long customerId) {
        String sql = "select * from hoadon where makh = '" + customerId + "' order by ngaymua desc";
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
        Connection connection = ConnectSQL.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, order.getOrderId());
            statement.setLong(2, order.getCustomerId());
            statement.setDate(3, new Date(order.getCreateDate().getTime()));
            statement.setBoolean(4, order.isStatus());
            connection.setAutoCommit(false);
            if (statement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
            connection.rollback();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            ConnectSQL.setAutocommit(connection, true);
        }
        return false;
    }
}
