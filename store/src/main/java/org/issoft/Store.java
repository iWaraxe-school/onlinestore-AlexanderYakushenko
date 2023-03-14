package org.issoft;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Store {
    private final List<Category> categoryList = new ArrayList<>();

    public void addCategory(Category category) {
        categoryList.add(category);
    }

    public void printData() {
        for (Category category : categoryList) {
            category.printCategoryName();
            category.printProducts();
        }
    }

    public void sortByXml() {
        Map<String, String> fieldToSort = XMLParser.parseXml();
        Comparator<Product> comparator = getComparator(fieldToSort);

        for (Category category : categoryList) {
            category.getProductList().sort(comparator);
        }
    }

    private Comparator<Product> getComparator(Map<String, String> fieldToSort) {
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
            for (int i = 0; i < 5; i++) {
                System.out.println(category.getProductList().get(i));
            }
        }
    }
}


