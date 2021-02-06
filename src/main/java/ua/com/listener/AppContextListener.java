package ua.com.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application context listener.
 *
 * @author Orest Dmyterko
 */
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        dbInit();
        commandRegister();
    }

    /**
     * Initializes connection to database.
     */
    private void dbInit(){
        try {
            Class.forName("ua.com.util.DBUtil");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Registers all command.
     */
    private void commandRegister(){
        try {
            Class.forName("ua.com.command.Invoker");
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
