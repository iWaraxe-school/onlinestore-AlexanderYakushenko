package org.issoft;

import com.github.javafaker.Faker;

public class RandomProductGenerator {
    private static final Faker FAKER = new Faker();


    private Double generatePrice() {

        return FAKER.number().randomDouble(2, 0, 1000);
    }

    private Double generateRate() {

        return FAKER.number().randomDouble(1, 0, 5);
    }

    private String generateName(String categoryName) {

        if (categoryName.equals("Bike")) {
            return FAKER.beer().name();
        }
        if (categoryName.equals("Milk")) {
            return FAKER.food().ingredient();
        }
        if (categoryName.equals("Phone")) {
            return FAKER.animal().name();
        }
        return null;
    }

    public Product generateProduct(String categoryName) {

        return new Product(generateName(categoryName), generatePrice(), generateRate());
    }

}
