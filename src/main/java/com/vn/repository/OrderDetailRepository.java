package com.vn.repository;

import com.vn.Utils;
import com.vn.model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrderDetailRepository {

    public List<OrderDetail> findByOrderId(long orderId) {
        String sql = "select * from chiTietHoaDon where mahoadon = " + orderId;
        try {
            Statement statement = ConnectSQL.getInstance().createStatement();
            return Utils.orderDetailMappers(statement.executeQuery(sql));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    public boolean insertOrderDetails(OrderDetail orderDetail) {
        String sql = " insert into chiTietHoaDon(maChiTietHD,maSach,soLuongMua,MaHoaDon) values(?,?,?,?)";
        Connection connection = ConnectSQL.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, orderDetail.getOrderDetailId());
            statement.setString(2, orderDetail.getProductId());
            statement.setInt(3, orderDetail.getQuantity());
            statement.setLong(4, orderDetail.getOrderId());
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
