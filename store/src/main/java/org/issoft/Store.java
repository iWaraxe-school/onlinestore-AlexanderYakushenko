package org.issoft;
import java.util.*;

public class Store {
    private List<Category> categoryList = new ArrayList<>();

    public void addCategory (Category category) {
        categoryList.add(category);
    }

    public void printData() {
        for (Category category : categoryList) {
            category.printCategoryName();
            category.printProducts();
        }
    }

    public List<Product> sortByXml() {
        List<Product> allProducts = getAllProducts();

        Map<String, String> fieldToSort = XMLParser.parseXml();
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
        allProducts.sort(generalComparator);
        return allProducts;
    }

        private List<Product> getAllProducts() {
            List<Product> allProducts = new ArrayList<>();
            for (Category category : categoryList) {
                allProducts.addAll(category.getProductList());
            }
            return allProducts;
        }
    }


