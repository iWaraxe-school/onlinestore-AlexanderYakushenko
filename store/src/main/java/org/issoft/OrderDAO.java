package org.issoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.issoft.DbConstants.*;

public class OrderDAO {
    private DBConnectionManager connectionManager;
    public OrderDAO(DBConnectionManager connectionManager){this.connectionManager = connectionManager;}

    public void addProductToOrders(Product product) throws SQLException {
        try(Connection connection = DBConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_QUERY)) {
            statement.setInt(1, product.getCategoryId());
            statement.setInt(2, product.getId());
            statement.setString(3, product.getName());
            statement.setDouble(4, product.getRate());
            statement.setDouble(5, product.getPrice());
            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating order with product: " + product + " error: " +e.getMessage());
        }
    }

    public int getLastOrderId() throws SQLException {
        try (Connection connection = DBConnectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(LAST_ORDER_ID);
             ResultSet resultSet = statement.executeQuery()){
            if (resultSet.next()){
                return resultSet.getInt(1);
            }
        }catch (SQLException e) {
            System.err.println("No orders found error: "+ e.getMessage());
        }
        return  -1;
    }
    public void clearOrdersTable() throws SQLException{
        try (Connection connection = DBConnectionManager.getConnection()){
             PreparedStatement statement = connection.prepareStatement(CLEAR_ORDER_QUERY);
             statement.executeUpdate();
             System.err.println("Orders table cleared.");
        } catch (SQLException e){
        System.err.println("Failed to clear orders table: " + e.getMessage());
    }
    }
}
