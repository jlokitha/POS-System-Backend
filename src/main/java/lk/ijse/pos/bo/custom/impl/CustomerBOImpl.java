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
    public boolean saveCustomer(CustomerDTO dto) {
        try {
            return customerDAO.save(dto.toEntity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) {
        try {
            return customerDAO.update(dto.toEntity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CustomerDTO findCustomerById(int id) {
        try {
            Customer customer = customerDAO.getData(id);

            if (customer != null) {
                return customer.toDto();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteCustomer(int id) {
        try {
            return customerDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        try {
            List<Customer> customers = customerDAO.getAll();

            if (customers != null) {

                List<CustomerDTO> dtos = new ArrayList<>();

                for (Customer customer : customers) {
                    dtos.add(customer.toDto());
                }

                return dtos;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
