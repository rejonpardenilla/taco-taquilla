package DataAccess;

import org.postgresql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String URL = "jdbc:postgresql://localhost:5432/taquilla";
    public static final String USER = "postgres";
    public static final String PASS = "postgres";

    public static Connection getConnection()
    {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
        System.out.println("hola");
    }
}