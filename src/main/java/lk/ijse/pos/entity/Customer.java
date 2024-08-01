package lk.ijse.pos.entity;

import lk.ijse.pos.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    private int id;
    private String name;
    private String address;
    private double salary;

    public CustomerDTO toDto() {
        return new CustomerDTO(id, name, address, salary);
    }
}
