package org.issoft;

public class Product {
    private final int  id;
    private final String name;
    private final Double price;
    private final Double rate;
    private final int categoryId;

    private Product(int id, String name, Double price, Double rate, int categoryId){
        this.id = id;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.categoryId = categoryId;
    }

    //Builder pattern
    public static ProductBuilder newProductBuilder(){
        return new ProductBuilder();
    }
    public int getId() {
        return id;
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
    public int getCategoryId() {
        return  categoryId;
    }

    @Override
    public String toString() {
        return String.format("Name:'%s', Price: %s,Rate: %s", name, price, rate);
    }

    public static class ProductBuilder {
        private int id;
        private String name;
        private Double price;
        private Double rate;
        private int categoryId;

        public ProductBuilder setId(int id){
            this.id = id;
            return this;
}
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
        public Product build() {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name must not be empty");
            }
            if (price == null || price <= 0) {
                throw new IllegalArgumentException("Price must be greater than 0");
            }
            return new Product(id, name, price, rate, categoryId);
        }
    }

}

