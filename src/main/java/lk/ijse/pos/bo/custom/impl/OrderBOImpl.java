package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.dto.OrderDTO;

import java.util.List;

public class OrderBOImpl implements OrderBO {
    @Override
    public boolean saveOrder(OrderDTO dto) {
        return false;
    }

    @Override
    public boolean updateOrder(OrderDTO dto) {
        return false;
    }

    @Override
    public OrderDTO findOrderById(int id) {
        return null;
    }

    @Override
    public boolean deleteOrder(int id) {
        return false;
    }

    @Override
    public List<OrderDTO> findAllOrders() {
        return List.of();
    }
}
