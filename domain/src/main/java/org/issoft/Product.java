package org.issoft;

public class Product {
    private String name;
    private Double price;
    private Double rate;

    //Builder pattern
    public static ProductBuilder newProductBuider(){
        return new Product().new ProductBuilder();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public class ProductBuilder{
        private String name;
        private double rate;
        private double price;

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
        public Product build(){
            Product.this.name = this.name;
            Product.this.price = this.price;
            Product.this.rate = this.rate;
            return Product.this;
        }
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        String productInfo = String.format("Name:'%s', Price: %s,Rate: %s", name, price, rate);
        return productInfo;
    }
}

