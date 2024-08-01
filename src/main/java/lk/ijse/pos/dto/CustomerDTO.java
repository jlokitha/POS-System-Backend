package lk.ijse.pos.dto;

import lk.ijse.pos.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private int id;
    private String name;
    private String address;
    private double salary;

    public Customer toEntity() {
        return new Customer(id, name, address, salary);
    }
}
