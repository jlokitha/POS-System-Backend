package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO dto);

    boolean updateCustomer(CustomerDTO dto);

    CustomerDTO findCustomerById(int id);

    boolean deleteCustomer(int id);

    List<CustomerDTO> findAllCustomers();
}
