package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO dto) throws SQLException;

    boolean updateCustomer(CustomerDTO dto) throws SQLException;

    CustomerDTO findCustomerById(int id) throws SQLException;

    boolean deleteCustomer(int id) throws SQLException;

    List<CustomerDTO> findAllCustomers() throws SQLException;
}
