package db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class DbConstants {
    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = DbConstants.class.getResourceAsStream("/db.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database configuration", e);
        }
    }

    public static final String URL = properties.getProperty("db.url");
    public static final String USER = properties.getProperty("db.user");
    public static final String PASSWORD = properties.getProperty("db.password");
    String DROP_TABLE_IF_EXIST = "DROP TABLE IF EXISTS %s";
    String CREATE_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS CATEGORIES ("+
            "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
            "NAME VARCHAR(255) NOT NULL);";
    String CREATE_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS PRODUCTS ("+
            "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
            "CATEGORY_ID INT NOT NULL," +
            "NAME VARCHAR(255) NOT NULL," +
            "RATE DECIMAL(10,1) NOT NULL" +
            "PRICE DECIMAL(10,2) NOT NULL" +
            "FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID));";
    String CREATE_ORDER_TABLE = "CREATE TABLE IF NOT EXISTS ORDERS ("+
            "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
            "NAME VARCHAR(255) NOT NULL," +
            "RATE DOUBLE NOT NULL" +
            "PRICE DOUBLE NOT NULL);";
    String INSERT_INTO_CATEGORIES = "INSERT INTO CATEGORIES(NAME) VALUES(?)";
    String INSERT_INTO_PRODUCTS = "INSERT INTO PRODUCTS(Category_Id, NAME, Rate, Price) VALUES(?,?,?,?)";
    String INSERT_INTO_ORDERS = "INSERT INTO ORDERS(NAME, RATE, PRICE) VALUES(?,?,?)";
    String SELECT_CATEGORIES = "SELECT ID, NAME FROM CATEGORIES";
    String SELECT_RANDOM_PRODUCT = "SELECT NAME, PRICE, RATE FROM PRODUCTS ORDER BY RAND () LIMIT 1";
    String SELECT_PRODUCT_BY_CATEGORY_ID = "SELECT NAME, PRICE, RATE FROM PRODUCTS WHERE CATEGORY_ID = %s";
    String SELECT_TOP5_PRODUCTS = "SELECT NAME, PRICE, RATE FROM PRODUCTS ORDER BY PRICE DESC LIMIT 5";
    String SELECT_PRODUCTS_SORTED_BY_XML = "SELECT NAME, PRICE, RATE FROM PRODUCTS ORDER BY";
    String SELECT_PRODUCTS_FROM_ORDERS = "SELECT NAME, PRICE, RATE FROM ORDERS";
    String INSERT_ORDER_QUERY = "INSERT INTO ORDERS (CATEGORY_ID, PRODUCT_ID, NAME, RATE, PRICE) VALUES(?,?,?,?,?)";
    String LAST_ORDER_ID = "SELECT ID ORDERS ORDER BY ID DESC LIMIT 1";
    String CLEAR_ORDER_QUERY = "TRUNCATE TABLE ORDERS";
    String DELETE_PRODUCT = "DELETE FROM PRODUCTS WHERE NAME=?";
    String UPDATE_PRODUCT = "UPDATE PRODUCTS SET PRICE=? WHERE NAME=?";
    String ALL_PRODUCTS = "SELECT * FROM PRODUCTS";
    String ALL_CATEGORIES = "SELECT * FROM CATEGORIES";
    String DELETE_CATEGORY = "DELETE FROM CATEGORIES WHERE ID=?";
    String UPDATE_CATEGORY = "UPDATE CATEGORIES SET NAME=? WHERE ID=?";
}
