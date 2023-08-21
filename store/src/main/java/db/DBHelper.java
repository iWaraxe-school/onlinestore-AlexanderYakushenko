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
//            URL;
//            USER= user;
//            PASSWORD = password;

            Store store = Store.getStore();

            clearDatabase();
            createCategoryTable();
            createProductTable();
         //   fillDatabase(store);
         //   printDBData();
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

                //statement.executeUpdate("DROP TABLE IF EXISTS PRODUCTS");
                statement.executeUpdate(DROP_TABLE_IF_EXIST);
                //statement.executeUpdate("DROP TABLE IF EXISTS CATEGORIES");

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
                //statement.executeUpdate( "CREATE TABLE IF NOT EXISTS CATEGORIES(" +
                //        "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, NAME VARCHAR(255) NOT NULL)");
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
//                statement.executeUpdate("CREATE TABLE IF NOT EXISTS PRODUCTS(" +
//                        "ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
//                        "CATEGORY_ID INT NOT NULL," +
//                        "NAME VARCHAR(255) NOT NULL, " +
//                        "RATE DECIMAL(10,1 ) NOT NULL,  " +
//                        "PRICE DECIMAL(10,1 ) NOT NULL, " +
//                        "FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID))");
            }
        }
//    private static Map<Category, Integer> createCategoryMap() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        Map<Category, Integer> categoryToPut = new HashMap<>();
//
//        Reflections reflections = new Reflections("by.issoft.domain.categories");
//
//        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);
//
//        for (Class<? extends Category> type : subTypes) {
//            Random random = new Random();
//            categoryToPut.put( CategoryFactory.getCategory(type), random.nextInt(10) + 1);
//        }
//        return categoryToPut;
//    }

//         public void fillStoreRandomly() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//              RandomStorePopulator populator = new RandomStorePopulator();
//              Map<Category, Integer> categoryMap = createCategoryMap();
//           int index =1;
//           for (Map.Entry<Category, Integer> entry : categoryMap.entrySet()) {
//               addCategoryToDB(entry.getKey());
//               addProductToDB(entry, populator,index);
//            index++;
//        }
//    }

//         private void addCategoryToDB(Category category) {
//            try {
//              PreparedStatement insertCategories = CONNECTION.prepareStatement("INSERT INTO CATEGORIES(NAME)"
//                    + " VALUES (?)");
//              insertCategories.setString(1, category.getName());
//              System.out.println(insertCategories);
//              insertCategories.execute();
//            } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//        private void addProductToDB(Map.Entry<Category, Integer> entry,RandomStorePopulator populator, int categoryID ){
//            for (int i = 0; i < entry.getValue(); i++) {
//                try {
//                    PreparedStatement insertProducts = CONNECTION.prepareStatement("INSERT INTO PRODUCTS (CATEGORY_ID,NAME,RATE,PRICE)"
//                        + "VALUES (?,?,?,?)");
//                    insertProducts.setInt (1, categoryID);
//                    insertProducts.setString (2,populator.getName(entry.getKey().getName()));
//                    insertProducts.setDouble (3,populator.getRate());
//                    insertProducts.setDouble (4,populator.getPrice());
//                    System.out.println(insertProducts);
//                    insertProducts.execute();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }



//        /**
//         * Fills the database with data from the specified store.
//         * @param store the store to get the data from
//         * @throws SQLException if an error occurs while executing the SQL statements
//         */
//        public void fillDatabase(Store store) throws SQLException {
//            try (Connection connection = getConnection();
//                 PreparedStatement categoryStatement = connection.prepareStatement(
//                         "INSERT INTO CATEGORIES (NAME) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
//                 PreparedStatement productStatement = connection.prepareStatement(
//                         "INSERT INTO PRODUCTS (CATEGORY_ID, NAME, RATE, PRICE) VALUES (?, ?, ?, ?)")) {
//                RandomStorePopulator populator = new RandomStorePopulator();
//                Map<Category, Integer> categoryProducts = store.  //createProductList();
//                for (Map.Entry<Category, Integer> entry : categoryProducts.entrySet()) {
//                    Category category = entry.getKey();
//                    int numProducts = entry.getValue();
//                    categoryStatement.setString(1, category.getName());
//                    categoryStatement.executeUpdate();
//                    try (ResultSet keys = categoryStatement.getGeneratedKeys()) {
//                        if (keys.next()) {
//                            int categoryId = keys.getInt(1);
//                            for (int i = 0; i < numProducts; i++) {
//                                Product product = populator.getProduct(category.getName());
//                                productStatement.setInt(1, categoryId);
//                                productStatement.setString(2, product.getName());
//                                productStatement.setDouble(3, product.getRate());
//                                productStatement.setDouble(4, product.getPrice());
//                                productStatement.executeUpdate();
//                            }
//                        } else {
//                            throw new SQLException("Failed to insert category: " + category);
//                        }
//                    }
//                }
//            }
//        }

//        private void printDBData()throws SQLException {
//            try (Connection connection = getConnection();
//                 Statement statement = connection.createStatement()) {
//                String sql = "SELECT CATEGORIES.NAME AS CATEGORY_NAME, PRODUCTS.NAME, PRODUCTS.RATE, PRODUCTS.PRICE "
//                        + "FROM CATEGORIES INNER JOIN PRODUCTS ON CATEGORIES.ID = PRODUCTS.CATEGORY_ID";
//                try (ResultSet resultSet = statement.executeQuery(sql)) {
//                    while (resultSet.next()) {
//                        String categoryName = resultSet.getString("CATEGORY_NAME");
//                        String productName = resultSet.getString("NAME");
//                        double rate = resultSet.getDouble("RATE");
//                        double price = resultSet.getDouble("PRICE");
//                        System.out.printf("%s: %s, rate=%.1f, price=%.1f%n", categoryName, productName, rate, price);
//                    }
//                }
//            }
//        }


    }
