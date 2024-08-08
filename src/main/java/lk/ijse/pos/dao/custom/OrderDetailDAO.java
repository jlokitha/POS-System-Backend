package lk.ijse.pos.dao.custom;

import lk.ijse.pos.entity.OrderDetail;
import lk.ijse.pos.utils.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends CrudUtil<OrderDetail> {
    boolean saveOrderDetail(OrderDetail orderDetail, Connection connection) throws SQLException;
    List<OrderDetail> getAll(int orderId) throws SQLException;
}
