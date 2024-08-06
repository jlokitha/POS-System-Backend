package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.OrderDTO;

import java.util.List;

public interface OrderBO extends SuperBO {
    boolean saveOrder(OrderDTO dto);

    boolean updateOrder(OrderDTO dto);

    OrderDTO findOrderById(int id);

    boolean deleteOrder(int id);

    List<OrderDTO> findAllOrders();
}
