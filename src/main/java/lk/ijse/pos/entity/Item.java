package lk.ijse.pos.entity;

import lk.ijse.pos.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    private int id;
    private String description;
    private int quantity;
    private double price;

    public ItemDTO toDto() {
        return new ItemDTO(id, description, quantity, price);
    }
}
