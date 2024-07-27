import com.javafinalsprint.dao.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseConnection.getConnection(); // Accessing getConnection() statically

        if (connection != null) {
            System.out.println("Database connected successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}

