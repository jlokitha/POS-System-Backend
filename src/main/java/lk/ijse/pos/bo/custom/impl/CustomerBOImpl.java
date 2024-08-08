package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO =
            (CustomerDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.save(dto.toEntity());
    }
    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.update(dto.toEntity());
    }
    @Override
    public CustomerDTO findCustomerById(int id) throws SQLException {
        Customer customer = customerDAO.getData(id);

        if (customer != null) {
            return customer.toDto();
        }
        return null;
    }
    @Override
    public boolean deleteCustomer(int id) throws SQLException {
        return customerDAO.delete(id);
    }
    @Override
    public List<CustomerDTO> findAllCustomers() throws SQLException {
        List<Customer> customers = customerDAO.getAll();

        List<CustomerDTO> dtos = new ArrayList<>();

        for (Customer customer : customers) {
            dtos.add(customer.toDto());
        }
        return dtos;
    }
}
