package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDetailDAO;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    final static String SAVE_ORDER_DETAIL = "INSERT INTO `order_detail` VALUES (?,?,?,?)";
    final static String GET_DETAILS = "SELECT * FROM `order_detail` WHERE `order_id` = ?";

    private Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public boolean saveOrderDetail(OrderDetail entity, Connection connection) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(SAVE_ORDER_DETAIL);
        stm.setInt(1, entity.getOrderId());
        stm.setInt(2, entity.getItemId());
        stm.setInt(3, entity.getQuantity());
        stm.setDouble(4, entity.getPrice());
        int saveItem = stm.executeUpdate();

        if (saveItem != 0) return true;
        return false;
    }
    @Override
    public List<OrderDetail> getAll(int orderId) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(GET_DETAILS);
        stm.setInt(1, orderId);
        ResultSet rs = stm.executeQuery();
        List<OrderDetail> details = new ArrayList<>();

        while (rs.next()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(rs.getInt(1));
            orderDetail.setItemId(rs.getInt(2));
            orderDetail.setQuantity(rs.getInt(3));
            orderDetail.setPrice(rs.getDouble(4));
            details.add(orderDetail);
        }

        if (!details.isEmpty()) return details;
        return null;
    }
    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
    @Override
    public boolean update(OrderDetail entity) throws SQLException {
        return false;
    }
    @Override
    public OrderDetail getData(int id) throws SQLException {
        return null;
    }
    @Override
    public List<OrderDetail> getAll() throws SQLException {
        return List.of();
    }
    @Override
    public boolean save(OrderDetail entity) throws SQLException {
        return false;
    }
}
