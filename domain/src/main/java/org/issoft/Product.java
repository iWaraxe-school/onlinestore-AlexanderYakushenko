package org.issoft;

public class Product {
    private int  id;
    private String name;
    private Double price;
    private Double rate;
    private int categoryId;

    private Product(){
    }

    //Builder pattern
    public static ProductBuilder newProductBuilder(){
        return new Product().new ProductBuilder();
    }

    public int getCategoryId() {
        return  categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Name:'%s', Price: %s,Rate: %s", name, price, rate);
    }

    public class ProductBuilder{
        private String name;
        private double rate;
        private double price;
        private int categoryId;

        public ProductBuilder setName(String name){
            this.name = name;
            return this;
        }
        public ProductBuilder setPrice(double price){
            this.price = price;
            return this;
        }
        public ProductBuilder setRate(double rate){
            this.rate = rate;
            return this;
        }
        public ProductBuilder setCategoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }
        public Product build(){
            Product.this.name = this.name;
            Product.this.price = this.price;
            Product.this.rate = this.rate;
            Product.this.categoryId = this.categoryId;
            return Product.this;
        }
    }

    public Double getPrice() {
        return price;
    }

    public Double getRate() {
        return rate;
    }


}

