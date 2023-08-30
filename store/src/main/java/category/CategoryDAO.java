package category;

import db.DBConnectionManager;
import org.issoft.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static db.DbConstants.*;

public class CategoryDAO {

    private DBConnectionManager connectionManager;
    private static final Logger logger = LoggerFactory.getLogger(CategoryDAO.class);
    public CategoryDAO(DBConnectionManager connectionManager){this.connectionManager = connectionManager;}

    public Category createCategory(String categoryName ){
        Category category = null;
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_CATEGORIES)){
                statement.setString(1, categoryName);
                statement.executeUpdate();
                category = new Category(categoryName);
        }catch(SQLException e){
                handleSQLException("Error creating category",e);
        }
        return category;
    }

    public void deleteCategory(Category category){
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY)){
            statement.setInt(1, category.getId());
            statement.executeUpdate();
        }catch(SQLException e){
            handleSQLException("Error deleting category",e);
        }
    }

    public void updateCategory(Category category){
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY)){
            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());
            statement.executeUpdate();
        }catch(SQLException e){
            handleSQLException("Error updating category", e);
        }
    }

    public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(ALL_CATEGORIES);
        ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                Category category = new Category(resultSet.getString("Name"));
                category.setId(resultSet.getInt("ID"));
                categories.add(category);
            }
        } catch(SQLException e){
    handleSQLException("Error retrieving categories", e);
        }
        return categories;
    }

    private void handleSQLException(String message, SQLException e) {
        logger.error(message, e);
        throw new RuntimeException(message, e);
    }
}

