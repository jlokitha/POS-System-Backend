package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    final static String SAVE_ITEM = "INSERT INTO item (description, qty, price) VALUES (?,?,?)";
    final static String DELETE_ITEM = "DELETE FROM item WHERE id=?";
    final static String UPDATE_ITEM = "UPDATE item SET description=?, qty=?, price=? WHERE id=?";
    final static String GET_ITEM = "SELECT * FROM item WHERE id=?";
    final static String FIND_ALL = "SELECT * FROM item";
    final static String UPDATE_ITEM_QTY = "UPDATE item SET qty=qty-? WHERE id=?";

    private Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public boolean save(final Item entity) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(SAVE_ITEM);
        stm.setString(1, entity.getDescription());
        stm.setInt(2, entity.getQuantity());
        stm.setDouble(3, entity.getPrice());
        int save = stm.executeUpdate();

        if (save != 0) {
            return true;
        }
        return false;
    }
    @Override
    public boolean delete(final int id) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(DELETE_ITEM);
        stm.setInt(1, id);
        int delete = stm.executeUpdate();

        if (delete != 0) {
            return true;
        }
        return false;
    }
    @Override
    public boolean update(final Item entity) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(UPDATE_ITEM);
        stm.setString(1, entity.getDescription());
        stm.setInt(2, entity.getQuantity());
        stm.setDouble(3, entity.getPrice());
        stm.setInt(4, entity.getId());
        int update = stm.executeUpdate();

        if (update != 0) {
            return true;
        }
        return false;
    }
    @Override
    public Item getData(final int id) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(GET_ITEM);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            return new Item(
                    id,
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getDouble(4)
            );
        }
        return null;
    }
    @Override
    public List<Item> getAll() throws SQLException {
        PreparedStatement stm = connection.prepareStatement(FIND_ALL);
        ResultSet rs = stm.executeQuery();
        List<Item> items = new ArrayList<>();

        while (rs.next()) {
            items.add(new Item(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getDouble(4)
            ));
        }
        return items;
    }
    @Override
    public boolean updateQty(final int id, final int qty, final Connection connection) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(UPDATE_ITEM_QTY);
        stm.setInt(1, qty);
        stm.setInt(2, id);
        int update = stm.executeUpdate();

        if (update != 0) {
            return true;
        }
        return false;
    }
}
