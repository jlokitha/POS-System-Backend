package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailDAO;
import lk.ijse.pos.dao.custom.impl.OrderDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetail;
import lk.ijse.pos.utils.TransactionUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    private final OrderDAO orderDAO =
            (OrderDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    private final OrderDetailDAO orderDetailDAO =
            (OrderDetailDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER_DETAIL);

    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException {
        boolean result = false;

        try {
            TransactionUtil.startTransaction();
            int saved = orderDAO.saveOrder(dto.toEntity());

            if (saved != -1) {
                List<OrderDetailDTO> list = dto.getItemList();
                for (OrderDetailDTO item : list) {
                    item.setOrderId(saved);
                    orderDetailDAO.save(item.toEntity());
                }
                result = true;
            }
        } catch (SQLException e) {
            TransactionUtil.rollBack();
            e.printStackTrace();
        } finally {
            TransactionUtil.endTransaction();
        }
        return result;
    }
    @Override
    public List<OrderDetailDTO> findOrderDetailsById(int id) {
        try {
            List<OrderDetail> all = orderDetailDAO.getAll(id);
            if (!all.isEmpty()) {
                List<OrderDetailDTO> list = new ArrayList<>();
                for (OrderDetail detail : all) {
                    list.add(detail.toDto());
                }
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<OrderDTO> findAllOrders() {
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("null");
        return null;
    }
}
