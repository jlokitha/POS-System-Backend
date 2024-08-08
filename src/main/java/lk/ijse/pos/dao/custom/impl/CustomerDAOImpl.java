package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    final static String SAVE_CUSTOMER = "INSERT INTO customer (name, address, salary) VALUES (?,?,?)";
    final static String UPDATE_CUSTOMER = "UPDATE customer SET name=?, address=?, salary=? WHERE id=?";
    final static String GET_CUSTOMER = "SELECT * FROM customer WHERE id=?";
    final static String FIND_ALL = "SELECT * FROM customer";
    final static String DELETE_CUSTOMER = "DELETE FROM customer WHERE id=?";

    private Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public boolean save(Customer customer) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(SAVE_CUSTOMER);
        stm.setString(1, customer.getName());
        stm.setString(2, customer.getAddress());
        stm.setDouble(3, customer.getSalary());
        int save = stm.executeUpdate();

        if (save != 0) {
            customer.setId(save);
            return true;
        }
        return false;
    }
    @Override
    public boolean update(Customer customer) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(UPDATE_CUSTOMER);
        stm.setString(1, customer.getName());
        stm.setString(2, customer.getAddress());
        stm.setDouble(3, customer.getSalary());
        stm.setInt(4, customer.getId());
        int update = stm.executeUpdate();

        if (update != 0) {
            return true;
        }
        return false;
    }
    @Override
    public Customer getData(int id) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(GET_CUSTOMER);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            return new Customer(
                    id,
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getDouble("salary")
            );
        }
        return null;
    }
    @Override
    public List<Customer> getAll() throws SQLException {
        PreparedStatement stm = connection.prepareStatement(FIND_ALL);
        ResultSet rs = stm.executeQuery();
        List<Customer> customers = new ArrayList<>();

        while (rs.next()) {
            customers.add(new Customer(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4)
            ));
        }
        return customers;
    }
    @Override
    public boolean delete(int id) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(DELETE_CUSTOMER);
        stm.setInt(1, id);
        int delete = stm.executeUpdate();

        if (delete != 0) {
            return true;
        }
        return false;
    }
}
