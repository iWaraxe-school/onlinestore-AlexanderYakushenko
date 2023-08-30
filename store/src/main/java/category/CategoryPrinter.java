package category;
import org.issoft.Product;

import java.util.List;
public class CategoryPrinter {

    public static void printProducts(List<Product> productList) {
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    public static void printCategoryName(String categoryName) {
        System.out.println(categoryName);
    }
}