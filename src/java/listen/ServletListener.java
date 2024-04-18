package listen;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Web application lifecycle listener.
 *
 * @author amrka
 */
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PreparedStatement stmt = null; // Declare PreparedStatement
        try {
            // Load database driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establish connection
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Password123");
            // Prepare statement
            stmt = conn.prepareStatement("SELECT * FROM Customer where username =? and password=? ");
            // Get servlet context
            ServletContext context = sce.getServletContext();
            // Set attribute in servlet context
            context.setAttribute("stmt", stmt);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup resources if needed
    }
}
