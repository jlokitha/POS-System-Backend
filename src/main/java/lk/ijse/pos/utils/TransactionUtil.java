package lk.ijse.pos.utils;

import lk.ijse.pos.db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionUtil {
    static Connection connection;

    static {
        connection = DbConnection.getInstance( ).getConnection( );
    }
    public static void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }
    public static void endTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }
    public static void rollBack() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }
}