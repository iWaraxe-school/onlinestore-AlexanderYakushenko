package product;

import com.github.javafaker.Faker;
import org.issoft.Product;

import java.util.HashMap;
import java.util.Map;

public class RandomProductGenerator {
    private static final Faker FAKER = new Faker();
    private final Map<String, String> categoryToDefaultName = new HashMap<>();

    public RandomProductGenerator() {
        categoryToDefaultName.put("Bike", FAKER.beer().name());
        categoryToDefaultName.put("Milk", FAKER.food().ingredient());
        categoryToDefaultName.put("Phone", FAKER.animal().name());
    }

    private Double generatePrice() {
        return FAKER.number().randomDouble(2, 0, 1000);
    }

    private Double generateRate() {
        return FAKER.number().randomDouble(1, 0, 5);
    }

    private String generateName(String categoryName) {
        return categoryToDefaultName.getOrDefault(categoryName, FAKER.commerce().productName());
    }
        public Product generateProduct(String categoryName) {

        Product product = Product.newProductBuilder()
                .setName(generateName(categoryName))
                .setPrice(generatePrice())
                .setRate(generateRate())
                .build();
        return product;
    }
}
