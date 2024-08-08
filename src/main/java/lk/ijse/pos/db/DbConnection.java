package lk.ijse.pos.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {

    private static DbConnection dbConnection;
    private Connection connection;
    static Logger logger = LoggerFactory.getLogger(DbConnection.class);

    private DbConnection() {
        logger.info("DBConnection initialized");
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/POSSystem");
            connection = pool.getConnection();
            logger.debug("Connection Initialized : " + connection);
        } catch (SQLException | NamingException e) {
            logger.error(e.getMessage());
        }
    }

    public static DbConnection getInstance() {
        return (dbConnection == null) ? dbConnection = new DbConnection() : dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }
}
