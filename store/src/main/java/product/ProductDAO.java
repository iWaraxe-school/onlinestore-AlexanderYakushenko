package product;

import db.DBConnectionManager;
import org.issoft.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static db.DbConstants.*;
public class ProductDAO {
    private static final Logger logger = LoggerFactory.getLogger(ProductDAO.class);
    private DBConnectionManager connectionManager;

    public ProductDAO(DBConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    public void createProduct(Product product) throws SQLException {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_INTO_PRODUCTS)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setDouble(3, product.getRate());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error creating product", e);
        }
    }
    public void deleteProduct(Product product) throws SQLException {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting product", e);
        }
    }
    public void updateProduct(Product product) throws SQLException {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating category", e);
        }
    }
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(ALL_PRODUCTS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString("NAME"));
                product.setPrice(resultSet.getDouble("PRICE"));
                product.setRate(resultSet.getDouble("RATE"));
                products.add(product);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving products", e);
        }
        return products;
    }
    public void createAndUpdateProduct(Product product) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            connection.setAutoCommit(false);
            try {
                createProduct(connection, product);
                updateProduct(connection, product);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error("Transaction failed", e);
            }
        }
    }
}

