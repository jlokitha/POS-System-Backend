package lk.ijse.pos.dao.custom;

import lk.ijse.pos.entity.Order;
import lk.ijse.pos.utils.CrudUtil;

import java.sql.SQLException;

public interface OrderDAO extends CrudUtil<Order> {
    int saveOrder(Order entity) throws SQLException;
}
