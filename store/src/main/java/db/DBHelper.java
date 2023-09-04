package db;
import org.issoft.Store;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import static db.DbConstants.*;

public class DBHelper {

        private final String URL;
        private final String USER;
        private final String PASSWORD;

        public DBHelper(String URL, String USER, String PASSWORD) {
            this.URL = URL;
            this.USER = USER;
            this.PASSWORD = PASSWORD;

        }
    public void initializeDatabase() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            clearDatabase(statement);
            createCategoryTable(statement);
            createProductTable(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database initialization failed.", e);
        }
    }

        private Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }

        private void clearDatabase(Statement statement) throws SQLException {
                statement.executeUpdate(DROP_TABLE_IF_EXIST);
        }

        private void createCategoryTable(Statement statement) throws SQLException {
                statement.executeUpdate( CREATE_CATEGORY_TABLE);
        }

        private void createProductTable(Statement statement)throws SQLException {
                statement.executeUpdate(CREATE_PRODUCT_TABLE);
        }
    }
