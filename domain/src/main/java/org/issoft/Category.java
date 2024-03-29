package org.issoft;

import java.util.ArrayList;
import java.util.List;

public class Category {

        private int id;
        private String name;
        private List<Product> productList;
        public Category(String name) {
            this(name,0);
            //this.name = name;
          //  this.productList = new ArrayList<>();
        }
    public Category(String name, int id) {
        this.name = name;
        this.id = id;
        this.productList = new ArrayList<>();
    }
    public List<Product> getProductList() {
            return productList;
         }
        public String getName() {
            return name;
        }
    public void addProduct (Product product){
        productList.add(product);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    protected void printProducts() {
        for (Product product: productList) {
            System.out.println(product);
        }
    }
//    protected void printCategoryName(){
//        System.out.println(name);
//    }
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
