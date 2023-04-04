package org.issoft;


import java.util.Comparator;

public class ProductComparatorGenerator {

    //getComparator returns different comparator’s objects depending on parameter field value.
    //Factory method used
        public static Comparator<Product> getComparator(String field) {
            switch (field) {
                case "name":
                    return Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER);
                case "rate":
                    return Comparator.comparing(Product::getRate);
                case "price":
                    return Comparator.comparing(Product::getPrice);
                default:
                    System.out.println("Unknown Field " + field);
                    return null;
            }
        }
}

