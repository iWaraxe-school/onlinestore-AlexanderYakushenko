package org.issoft;

import java.util.ArrayList;
import java.util.List;

public class Category {

        private String name;
        private List<Product> productList;

        public Category(String name) {
            this.name = name;
            this.productList = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

    public void printProducts() {
        for (Product product: productList) {
            System.out.println(product);
        }
    }
    public void printCategoryName(){
        System.out.println(name);
    }

    public void addProduct (Product product){
            productList.add(product);

    }


}
