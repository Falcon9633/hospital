package ua.com.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This utility class allows you to connect to the database
 * using the connection pool.
 *
 * @author Orest Dmyterko
 * @version 1.0
 */
public class DBUtil {
    private static DataSource dataSource;

    static {
        try {
            InitialContext initialContext = new InitialContext();
            Context context = (Context) initialContext.lookup("java:comp/env");
            dataSource = (DataSource) context.lookup("jdbc/hospital");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private DBUtil() {
        //util class
    }

    /**
     * This method returns a connection from the pool
     * which must be configured in WEB-APP/META-INF/context.xml
     *
     * @return Connection
     * @throws SQLException – if the database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
