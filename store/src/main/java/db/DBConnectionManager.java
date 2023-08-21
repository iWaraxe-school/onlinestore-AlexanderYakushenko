package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

    static final String URL = "jdbc:mysql://localhost:3306/onlineStore" ;
    static final String USER = "root";
    static final String PASSWORD = "root12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection (Connection connection) throws SQLException{
        if (connection != null){
            connection.close();
        }
    }
}
