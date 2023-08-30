package product;

import db.DBConnectionManager;
import org.issoft.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static db.DbConstants.*;
public class ProductDAO {


    private DBConnectionManager connectionManager;
    public ProductDAO(DBConnectionManager connectionManager){this.connectionManager = connectionManager;}

    public void createProduct(Product product)throws SQLException{
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_INTO_PRODUCTS)){
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setDouble(3, product.getRate());
            statement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error creating product" + e.getMessage());
        }
    }

    public void deleteProduct(Product product)throws SQLException{
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT)){
            statement.setString(1, product.getName());
            statement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error deleting product" + e.getMessage());
        }
    }

    public void updateProduct(Product product)throws SQLException{
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)){
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error updating category" + e.getMessage());
        }
    }

    public List<Product> getAllProducts() throws SQLException {
        RandomProductGenerator productGenerator = new RandomProductGenerator();
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(ALL_PRODUCTS)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = productGenerator.generateProduct;

                resultSet.getString("NAME");
                product.setName(resultSet.getString("NAME"));
                products.add(product);
            }
        }catch(SQLException e){
                System.err.println("Error retrieving products" + e.getMessage());
            }
                return products;
            }
        }
