package org.issoft;

import org.apache.commons.exec.util.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

//import static org.issoft.XMLParser.fieldToSort;

public class Store {
    private static final Store store =new Store();
    int TOP_PRODUCTS_NUMBER =5;
    private final List<Category> categoryList = new ArrayList<>();

    public void addCategory(Category category) {
        categoryList.add(category);
    }
    //Singleton
    public static Store getStore(){
        return store;
    }
    public void createOrder() {
        Order order = new Order();
        order.start();
    }
    public void cleanOrder() {
        OrderCleaner orderCleaner = new OrderCleaner();
        orderCleaner.start();
    }
    public void printData() {
        for (Category category : categoryList) {
            System.out.println("***************");
            category.printCategoryName();
            System.out.println("---------------");
            category.printProducts();
            System.out.println("---------------");
        }
    }

    public void sortByXml() {
        Map<String, String> fieldToSort = XMLParser.parseXml();
        Comparator<Product> comparator = getComparator(fieldToSort);

        for (Category category : categoryList) {
            category.getProductList().sort(comparator);
        }
    }
    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        for (Category category : categoryList) {
            allProducts.addAll(category.getProductList());
        }
        return allProducts;
    }

    public Comparator<Product> getComparator(Map<String, String> fieldToSort) {
        List<Comparator<Product>> comparators = new ArrayList<>();
        for (Map.Entry<String, String> entry : fieldToSort.entrySet()) {

            Sorting sorting = Sorting.valueOf(entry.getValue());
            String field = entry.getKey();

            switch (sorting) {
                case ASC:
                    comparators.add(ProductComparatorGenerator.getComparator(field));
                    break;
                case DESC:
                    comparators.add(ProductComparatorGenerator.getComparator(field).reversed());
                    break;
            }
        }

        Comparator<Product> generalComparator = comparators.get(0);
        for (int i = 1; i < comparators.size(); i++) {
            generalComparator = generalComparator.thenComparing(comparators.get(i));
        }
        return generalComparator;
    }

    public void printTopProducts() {
        Comparator<Product> comparator = Comparator.comparing(Product::getRate).reversed();
        for (Category category : categoryList) {
            System.out.println(category.getName());
            category.getProductList().sort(comparator);
            for (int i = 0; i < TOP_PRODUCTS_NUMBER; i++) {
                System.out.println(category.getProductList().get(i));
            }
        }
    }
}


