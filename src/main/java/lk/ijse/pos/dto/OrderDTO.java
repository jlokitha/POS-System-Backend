package lk.ijse.pos.dto;

import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private int id;
    private LocalDate date;
    private double total;
    private double discount;
    private int customerId;
    private List<OrderDetailDTO> itemList;

    public Order toEntity() {
        return new Order(id, date, total, discount, customerId, toEntityList());
    }
    public List<OrderDetail> toEntityList() {
        return itemList.stream().map(itemList -> new OrderDetail(
                itemList.getOrderId(),
                itemList.getItemId(),
                itemList.getQuantity(),
                itemList.getPrice()
        )).toList();
    }
}
