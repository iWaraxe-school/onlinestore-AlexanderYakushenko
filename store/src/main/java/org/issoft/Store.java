package org.issoft;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categoryList = new ArrayList<>();
    private List<Product> productList;
    public void addCategory (Category category) {
        categoryList.add(category);
    }
}
