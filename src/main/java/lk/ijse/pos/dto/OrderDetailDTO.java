package lk.ijse.pos.dto;

import lk.ijse.pos.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDTO {
    private int orderId;
    private int itemId;
    private int quantity;
    private double price;

    public OrderDetail toEntity() {
        return new OrderDetail(orderId, itemId, quantity, price);
    }
}
