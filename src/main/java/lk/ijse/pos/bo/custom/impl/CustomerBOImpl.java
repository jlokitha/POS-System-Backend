package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO =
            (CustomerDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.CUSTOMER );

    @Override
    public CustomerDTO saveCustomer(CustomerDTO dto) {
        Customer save = customerDAO.save(dto.toEntity());

        if (save != null) {
            return save.toDto();
        }

        return null;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO dto) {
        Customer update = customerDAO.update(dto.toEntity());

        if (update != null) {
            return update.toDto();
        }

        return null;
    }

    @Override
    public CustomerDTO findCustomerById(int id) {
        Customer customer = customerDAO.findById(id);

        if (customer != null) {
            return customer.toDto();
        }

        return null;
    }

    @Override
    public boolean deleteCustomer(int id) {
        Customer customer = customerDAO.findById(id);

        if (customer != null) {
            customerDAO.delete(id);
            return true;
        }

        return false;
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        List<Customer> customers = customerDAO.findAll();

        if (customers != null) {

            List<CustomerDTO> dtos = new ArrayList<>();

            for (Customer customer : customers) {
                dtos.add(customer.toDto());
            }

            return dtos;
        }

        return null;
    }
}
