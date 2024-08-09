package lk.ijse.pos.dao.custom;

import lk.ijse.pos.entity.Item;
import lk.ijse.pos.utils.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItemDAO extends CrudUtil<Item> {
    boolean updateQty(int id, int qty, Connection connection) throws SQLException;
}
