package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class DBConnectionManager {

//    static final String URL = "jdbc:mysql://localhost:3306/onlineStore" ;
//    static final String USER = "root";
//    static final String PASSWORD = "root12345";
    private static final String CONFIG_FILE_PATH = "dbconfig.properties";
    private static DBConnectionManager instance = null;
    private static HikariCPDataSource dataSource;
    private DBConnectionManager(){
        dataSource = new HikariCPDataSource(CONFIG_FILE_PATH);
    }
    public static DBConnectionManager getInstance(){
        if (instance ==null){
            instance = new DBConnectionManager();
        }
        return instance;
    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeConnection (Connection connection) throws SQLException{
        if (connection != null){
            connection.close();
        }
    }
}
