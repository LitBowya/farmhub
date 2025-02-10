package farmhub.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/farmhub"; // Database URL
    private static final String USER = "root"; // Default XAMPP MySQL user
    private static final String PASSWORD = ""; // Leave empty if no password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load JDBC driver
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database Connection Failed.");
            e.printStackTrace();
        }
        return conn;
    }
}
