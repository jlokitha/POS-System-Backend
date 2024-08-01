package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.Customer;

import java.util.List;

public interface CustomerDAO extends SuperDAO {

    Customer save(Customer customer);
    Customer update(Customer customer);
    Customer findById(int id);
    List<Customer> findAll();
    boolean delete(int id);
}
