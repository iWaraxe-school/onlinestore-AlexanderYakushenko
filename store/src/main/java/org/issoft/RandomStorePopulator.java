package org.issoft;
import org.reflections.Reflections;
import java.util.HashSet;
import java.util.Set;

public class RandomStorePopulator {

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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return categories;
    }

    public void fillStore() {
        Set<Category> categories = createCategories();
        RandomProductGenerator generator = new RandomProductGenerator();

        for (Category category : categories) {
            for (int i = 0; i < 10; i++) {
                Product product = generator.generateProduct(category.getName());
                category.addProduct(product);
            }
            store.addCategory(category);
        }


    }


}
