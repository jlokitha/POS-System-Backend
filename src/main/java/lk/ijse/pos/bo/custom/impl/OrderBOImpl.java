package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailDAO;
import lk.ijse.pos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrderDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    private final OrderDAO orderDAO =
            (OrderDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    private final OrderDetailDAO orderDetailDAO =
            (OrderDetailDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER_DETAIL);
    private final ItemDAO itemDAO =
            (ItemDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);

    Connection connection;

    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException {
        boolean result = false;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            int saved = orderDAO.saveOrder(dto.toEntity(), connection);

            if (saved != -1) {
                List<OrderDetailDTO> list = dto.getItemList();
                for (OrderDetailDTO item : list) {
                    item.setOrderId(saved);
                    orderDetailDAO.saveOrderDetail(item.toEntity(), connection);
                    itemDAO.updateQty(item.getItemId(), item.getQuantity(), connection);
                }
                result = true;
                connection.commit();
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
        return result;
    }
    @Override
    public List<OrderDetailDTO> findOrderDetailsById(int id) throws SQLException {
        List<OrderDetail> all = orderDetailDAO.getAll(id);

        if (!all.isEmpty()) {
            List<OrderDetailDTO> list = new ArrayList<>();

            for (OrderDetail detail : all) {
                list.add(detail.toDto());
            }
            return list;
        }
        return null;
    }

    @Override
    public List<OrderDTO> findAllOrders() throws SQLException {
        List<Order> all = orderDAO.getAll();

        if (!all.isEmpty()) {
            List<OrderDTO> list = new ArrayList<>();

            for (Order order : all) {
                list.add(order.toDto());
                System.out.println(order.toDto());
            }
            System.out.println(list);
            return list;
        }
        return null;
    }
}
