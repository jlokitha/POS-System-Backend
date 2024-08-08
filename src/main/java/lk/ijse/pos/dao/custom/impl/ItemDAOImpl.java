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
    final static String SAVE_ITEM = "INSERT INTO item (id, description, qty, price) VALUES (?,?,?,?)";
    final static String DELETE_CUSTOMER = "DELETE FROM item WHERE id=?";
    final static String UPDATE_ITEM = "UPDATE item SET description=?, qty=?, price=? WHERE id=?";
    final static String GET_CUSTOMER = "SELECT * FROM item WHERE id=?";
    final static String FIND_ALL = "SELECT * FROM item";

    private Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public boolean save(final Item entity) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(SAVE_ITEM);
        stm.setInt(1, entity.getId());
        stm.setString(2, entity.getDescription());
        stm.setInt(3, entity.getQuantity());
        stm.setDouble(4, entity.getPrice());
        int save = stm.executeUpdate();

        if (save != 0) {
            return true;
        }
        return false;
    }
    @Override
    public boolean delete(final int id) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(DELETE_CUSTOMER);
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
        PreparedStatement stm = connection.prepareStatement(GET_CUSTOMER);
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
}
