package org.issoft;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO {
    private static final String INSERT_ORDER_QUERY =
            "INSERT INTO ORDERS (CATEGORY_ID, PRODUCT_ID, NAME, RATE, PRICE) VALUES(?,?,?,?,?)";
    private DBConnectionManager connectionManager;
    public OrderDAO(DBConnectionManager connectionManager){this.connectionManager = connectionManager;}
    @SneakyThrows
    public void addProductToOrders(Product product){
        try(Connection connection = DBConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_QUERY)) {
            statement.setInt(1, product.getCategoryId());
            statement.setInt(2, product.getId());
            statement.setString(3, product.getName());
            statement.setDouble(3, product.getRate());
            statement.setDouble(3, product.getPrice());
            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating order with product: " + product + " error: " +e.getMessage());
            e.printStackTrace();
        }
    }

    public int getLastOrderId() {
        String query = "SELECT ID ORDERS ORDER BY ID DESC LIMIT 1";
        try (Connection connection = DBConnectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()){
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        }catch (SQLException e) {
            System.err.println("No orders found error: "+ e.getMessage());
            e.printStackTrace();
        }
        return  -1;
    }
    public void clearOrdersTable() {
        try (Connection connection = DBConnectionManager.getConnection()){
             String clearOrderQuery = "TRANCATE TABLE ORDERS";
             PreparedStatement statement = connection.prepareStatement(clearOrderQuery);
             statement.executeUpdate();
             System.err.println("Orders table cleared.");
        } catch (SQLException e){
        System.err.println("Failed to clear orders table: " + e.getMessage());
    }

    }
}
