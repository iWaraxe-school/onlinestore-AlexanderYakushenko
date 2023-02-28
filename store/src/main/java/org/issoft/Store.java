package org.issoft;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Store {
    private List<Category> categoryList = new ArrayList<>();

    public void addCategory (Category category) {
        categoryList.add(category);
    }


   // public Store() {
   //     this.categoryList = new HashSet<>();
   // }

    public void printData() {
        for (Category category : categoryList) {
            category.printCategoryName();
            category.printProducts();
        }
    }

}
