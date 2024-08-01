package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {
    CustomerDTO saveCustomer(CustomerDTO dto);

    CustomerDTO updateCustomer(CustomerDTO dto);

    CustomerDTO findCustomerById(int id);

    boolean deleteCustomer(int id);

    List<CustomerDTO> findAllCustomers();
}
