import ua.com.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtil.getConnection();
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM role");
            if (resultSet.next()){
                String login = resultSet.getString("name");
                System.out.println(login);
            }
            resultSet.close();
            resultSet = null;
            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null){
                    resultSet.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        super.service(req, resp);
    }
}
