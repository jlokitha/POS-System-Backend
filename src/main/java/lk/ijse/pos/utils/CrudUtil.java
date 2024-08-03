package lk.ijse.pos.utils;

import lk.ijse.pos.dao.SuperDAO;

import java.sql.SQLException;
import java.util.List;

public interface CrudUtil<T> extends SuperDAO {
    boolean save(final T entity) throws SQLException;

    boolean delete(final int id) throws SQLException;

    boolean update(final T entity) throws SQLException;

    T getData(final int id) throws SQLException;

    List<T> getAll() throws SQLException;
}