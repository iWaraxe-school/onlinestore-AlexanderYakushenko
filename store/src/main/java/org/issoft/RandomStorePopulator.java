package org.issoft;
import org.reflections.Reflections;
import product.RandomProductGenerator;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomStorePopulator {
    private static final Logger log = LoggerFactory.getLogger(RandomStorePopulator.class);
    private Store store;
    public RandomStorePopulator(Store store) {
        this.store = store;
    }
    public Set<Category> createCategories() {
        Set<Category> categories = new HashSet<>();
        Reflections reflections = new Reflections("org.issoft");
        Set<Class<? extends Category>> subCategoryClasses = reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> subCategoryClass : subCategoryClasses) {
            try {
                categories.add(subCategoryClass.getConstructor().newInstance());
            } catch (NoSuchMethodException e) {
                log.error("No default constructor found for class " + subCategoryClass.getName(), e);
            } catch (InstantiationException e) {
                log.error("Failed to instantiate class " + subCategoryClass.getName(), e);
            } catch (Exception e) {
                log.error("Unknown error creating category for class " + subCategoryClass.getName(), e);
            }
        }
        return categories;
    }

    public void fillStore() {
        Set<Category> categories = createCategories();
        RandomProductGenerator generator = new RandomProductGenerator();
        final int PRODUCTS_PER_CATEGORY = 10;
        for (Category category : categories) {
            for (int i = 0; i < PRODUCTS_PER_CATEGORY; i++) {
                Product product = generator.generateProduct(category.getName());
                category.addProduct(product);
            }
            store.addCategory(category);
        }
    }
}
