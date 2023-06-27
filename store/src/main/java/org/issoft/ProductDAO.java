package org.issoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {


    private DBConnectionManager connectionManager;
    public ProductDAO(DBConnectionManager connectionManager){this.connectionManager = connectionManager;}

    public void createProduct(Product product){
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODUCTS (NAME, PRICE, RATE) VALUES(?,?,?)")){
            statement.setString(1, product.getName().toString());
            statement.setDouble(2, product.getPrice().doubleValue());
            statement.setDouble(3, product.getRate().doubleValue());
            statement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error creating product" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteProduct(Product product){
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM PRODUCTS WHERE NAME=?")){
            statement.setString(1, product.getName());
            statement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error deleting product" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product){
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE PRODUCTS SET PRICE=? WHERE NAME=?")){
            statement.setString(1, product.getName().toString());
            statement.setDouble(2, product.getPrice().doubleValue());
            statement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error updating category" + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts(){
        RandomProductGenerator productGenerator = new RandomProductGenerator()
        //CategoryFactory categoryFactory = new CategoryFactory();
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnectionManager.getConnection());
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTS")){
        ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next())
                Product product = productGenerator.generateProduct()(resultSet.getString("Name"));
                product.setName(resultSet.getString("NAME"));
                products.add(product);

        } catch(SQLException e) {
            System.err.println("Error retrieving products" + e.getMessage());
            throw new RuntimeException();
        }
        return products;
    }


}

}
