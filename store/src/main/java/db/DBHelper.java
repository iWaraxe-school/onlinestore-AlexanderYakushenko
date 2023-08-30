package db;
import org.issoft.Store;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import static db.DbConstants.*;

public class DBHelper {

        static final String URL = "jdbc:mysql://localhost:3306/onlineStore" ;
        static final String USER = "user";
        static final String PASSWORD = "user";
        Connection CONNECTION = null;
        Statement  STATEMENT = null;
        Statement  STATEMENT_ENCLOSED = null;
        ResultSet RESULTSET = null;
        ResultSet RESULTSET_ENCLOSED = null;

        /**
         * Constructs a new DatabaseHelper with the specified database URL, username, and password.
         * @param URL the URL of the database to connect to
         * @param USER the username to use when connecting to the database
         * @param PASSWORD the password to use when connecting to the database
         */
        public DBHelper(String URL, String USER, String PASSWORD) throws SQLException {

            Store store = Store.getStore();
            clearDatabase();
            createCategoryTable();
            createProductTable();
        }

        private Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }

        /**
         * Clears the database by dropping the categories and products tables if they exist.
         * @throws SQLException if an error occurs while executing the SQL statements
         */
        private void clearDatabase() throws SQLException {
            try (Connection connection = getConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(DROP_TABLE_IF_EXIST);

            }
        }

        /**
         * Creates the categories table if it does not already exist.
         * @throws SQLException if an error occurs while executing the SQL statement
         */
        private void createCategoryTable() throws SQLException {
            try (Connection connection = getConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate( CREATE_CATEGORY_TABLE);
            }
        }

        /**
         * Creates the products table if it does not already exist.
         * @throws SQLException if an error occurs while executing the SQL statement
         */
        private void createProductTable()throws SQLException {
            try (Connection connection = getConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(CREATE_PRODUCT_TABLE);
            }
        }
    }
