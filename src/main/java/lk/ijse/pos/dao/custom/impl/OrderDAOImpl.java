package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public int saveOrder(Order entity) throws SQLException {
        String SAVE_ORDER = "INSERT INTO `order` VALUES (?,?,?,?,?)";

        PreparedStatement stm = connection.prepareStatement(SAVE_ORDER);
        stm.setInt(1, entity.getId());
        stm.setDate(2, Date.valueOf(entity.getDate()));
        stm.setDouble(3, entity.getTotal());
        stm.setDouble(4, entity.getDiscount());
        stm.setInt(5, entity.getCustomerId());
        int save = stm.executeUpdate();

        if (save != 0) return save;
        return -1;
    }
    @Override
    public Order getData(int id) throws SQLException {
        String GET_ORDER = "SELECT * FROM `order` WHERE `id`=?";

        PreparedStatement stm = connection.prepareStatement(GET_ORDER);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt(1));
            order.setDate(rs.getDate(2).toLocalDate());
            order.setTotal(rs.getDouble(3));
            order.setDiscount(rs.getDouble(4));
            order.setCustomerId(rs.getInt(5));
            return order;
        }
        return null;
    }
    @Override
    public List<Order> getAll() throws SQLException {
        String GET_ALL_ORDER = "SELECT * FROM `order`";

        PreparedStatement stm = connection.prepareStatement(GET_ALL_ORDER);
        ResultSet rs = stm.executeQuery();

        List<Order> orders = new ArrayList<>();
        while (rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt(1));
            order.setDate(rs.getDate(2).toLocalDate());
            order.setTotal(rs.getDouble(3));
            order.setDiscount(rs.getDouble(4));
            order.setCustomerId(rs.getInt(5));
            orders.add(order);
        }
        if (!orders.isEmpty()) return orders;
        return null;
    }
    @Override
    public boolean save(Order entity) throws SQLException {
        return false;
    }
    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Order entity) throws SQLException {
        return false;
    }
}
