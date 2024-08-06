package lk.ijse.pos.entity;

import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private int id;
    private LocalDate date;
    private double total;
    private double discount;
    private int customerId;
    private List<OrderDetail> itemList;

    public OrderDTO toDto() {
        return new OrderDTO(id, date, total, discount, customerId, toDtoList());
    }
    public List<OrderDetailDTO> toDtoList() {
        return itemList.stream().map(itemList -> new OrderDetailDTO(
                itemList.getOrderId(),
                itemList.getItemId(),
                itemList.getQuantity(),
                itemList.getPrice()
        )).toList();
    }
}
