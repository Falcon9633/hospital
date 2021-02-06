package ua.com.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
 */
public class DBUtil {
    private static final Logger LOGGER = LogManager.getLogger(DBUtil.class);
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
     * Returns a connection from the pool
     * which must be configured in WEB-APP/META-INF/context.xml
     *
     * @return Connection
     * @throws SQLException – if the database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        LOGGER.debug("DBUtil.getConnection starts");
        return dataSource.getConnection();
    }

    /**
     * Closes a resource or returns a connection to the connection pool.
     *
     * @param ac a resource to be closed
     * @throws Exception - if this resource cannot be closed
     */
    public static void closeResource(AutoCloseable ac) throws Exception {
        LOGGER.debug("DBUtil.close starts");
        if (ac != null) {
            ac.close();
        }
    }

    public static void closeResource(AutoCloseable... autoCloseables) throws Exception {
        for (AutoCloseable ac : autoCloseables) {
            closeResource(ac);
        }
    }
}
