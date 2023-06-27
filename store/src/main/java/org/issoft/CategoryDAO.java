package org.issoft;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private DBConnectionManager connectionManager;
    public CategoryDAO(DBConnectionManager connectionManager){this.connectionManager = connectionManager;}

  //  @SneakyThrows
    public void createCategory(Category category){
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CATEGORIES (NAME) VALUES(?)")){
                statement.setString(1, category.getName().toString());
                statement.executeUpdate();
        }catch(SQLException e){
                System.err.println("Error creating category" + e.getMessage());
                e.printStackTrace();
        }
    }

    public void deleteCategory(Category category){
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM CATEGORIES WHERE ID=?")){
            statement.setInt(1, category.getId());
            statement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error deleting category" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateCategory(Category category){
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE CATEGORIES SET NAME=? WHERE ID=?")){
            statement.setString(1, category.getName().toString());
            statement.setInt(1, category.getId());
            statement.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error updating category" + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Category> getAllCategories(){
        CategoryFactory categoryFactory = new CategoryFactory();
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DBConnectionManager.getConnection());
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM CATEGORIES"))
        ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                Category category = categoryFactory.createCategory(resultSet.getString("Name"));
                category.setId(resultSet.getInt("ID"));
                categories.add(category);
            }
        } catch(SQLException e){
    System.err.println("Error retrieving categories" + e.getMessage());
    throw new RuntimeException();
        }
        return categories;
    }
      //      statement.setString(1, category.getName().toString());
       //     statement.setInt(1, category.getId());
       //     statement.executeUpdate();
   // }

}

