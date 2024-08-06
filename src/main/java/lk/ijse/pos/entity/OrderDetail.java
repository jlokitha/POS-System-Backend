package lk.ijse.pos.entity;

import lk.ijse.pos.dto.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail {
    private int orderId;
    private int itemId;
    private int quantity;
    private double price;

    public OrderDetailDTO toDto() {
        return new OrderDetailDTO(orderId, itemId, quantity, price);
    }
}
