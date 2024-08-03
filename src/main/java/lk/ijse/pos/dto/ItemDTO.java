package lk.ijse.pos.dto;

import lk.ijse.pos.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private int id;
    private String description;
    private int quantity;
    private double price;

    public Item toEntity() {
        return new Item(id, description, quantity, price);
    }
}
