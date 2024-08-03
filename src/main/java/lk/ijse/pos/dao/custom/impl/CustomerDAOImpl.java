package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.dao.custom.CustomerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private Connection connection = DbConnection.getInstance().getConnection();

    @Override
    public boolean save(Customer customer) {
        try {
            String SAVE_CUSTOMER = "INSERT INTO customer (id, name, address, salary) VALUES (?,?,?,?)";

            PreparedStatement stm = connection.prepareStatement(SAVE_CUSTOMER);

            stm.setInt(1, customer.getId());
            stm.setString(2, customer.getName());
            stm.setString(3, customer.getAddress());
            stm.setDouble(4, customer.getSalary());

            int save = stm.executeUpdate();

            if (save != 0) {
                customer.setId(save);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Customer customer) {
        try {
            String UPDATE_CUSTOMER = "UPDATE customer SET name=?, address=?, salary=? WHERE id=?";

            PreparedStatement stm = connection.prepareStatement(UPDATE_CUSTOMER);

            stm.setString(1, customer.getName());
            stm.setString(2, customer.getAddress());
            stm.setDouble(3, customer.getSalary());
            stm.setInt(4, customer.getId());

            int update = stm.executeUpdate();

            if (update != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Customer getData(int id) {

        try {
            String GET_CUSTOMER = "SELECT * FROM customer WHERE id=?";

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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Customer> getAll() {

        try {
            String FIND_ALL = "SELECT * FROM customer";

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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean delete(int id) {

        try {
            String DELETE_CUSTOMER = "DELETE FROM customer WHERE id=?";

            PreparedStatement stm = connection.prepareStatement(DELETE_CUSTOMER);

            stm.setInt(1, id);

            int delete = stm.executeUpdate();

            if (delete != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
