package org.issoft;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Store {
    private ExecutorService executorService;
    private ScheduledExecutorService scheduledExecutorService;

    public Store() {
        this.executorService = Executors.newCachedThreadPool();
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }

    public void createOrder() {
        executorService.execute(new Order());
    }

    public void startOrderCleaner() {
        scheduledExecutorService.scheduleAtFixedRate(new OrderCleaner(), 2, 2, TimeUnit.SECONDS);
    }

    public void stop() {
        executorService.shutdown();
        scheduledExecutorService.shutdown();
    }

    public void shutdown() {
        if (executorService != null) {
            executorService.shutdown(); // Disable new tasks from being submitted
            try {
                // Wait a while for existing tasks to terminate
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow(); // Cancel currently executing tasks
                    // Wait a while for tasks to respond to being cancelled
                    if (!executorService.awaitTermination(60, TimeUnit.SECONDS))
                        System.err.println("ExecutorService did not terminate");
                }
            } catch (InterruptedException ie) {
                // (Re-)Cancel if current thread also interrupted
                executorService.shutdownNow();
                // Preserve interrupt status
                Thread.currentThread().interrupt();
            }
        }
    }
    private static final Store store =new Store();
    private static final int TOP_PRODUCTS_NUMBER =5;
    private final List<Category> categoryList = new ArrayList<>();

    public void addCategory(Category category) {
        categoryList.add(category);
    }
    //Singleton
    public static Store getStore(){
        return store;
    }
    public void cleanOrder() {
        executorService.execute(new OrderCleaner());
    }
    public void printData() {
        for (Category category : categoryList) {
            System.out.println("***************");
            category.printCategoryName();
            System.out.println("---------------");
            category.printProducts();
            System.out.println("---------------");
        }
    }

    public void sortByXml() {
        Map<String, String> fieldToSort = XMLParser.parseXml();
        Comparator<Product> comparator = getComparator(fieldToSort);

        for (Category category : categoryList) {
            category.getProductList().sort(comparator);
        }
    }
    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        for (Category category : categoryList) {
            allProducts.addAll(category.getProductList());
        }
        return allProducts;
    }

    public Comparator<Product> getComparator(Map<String, String> fieldToSort) {
        List<Comparator<Product>> comparators = new ArrayList<>();
        for (Map.Entry<String, String> entry : fieldToSort.entrySet()) {

            Sorting sorting = Sorting.valueOf(entry.getValue());
            String field = entry.getKey();

            switch (sorting) {
                case ASC:
                    comparators.add(ProductComparatorGenerator.getComparator(field));
                    break;
                case DESC:
                    comparators.add(ProductComparatorGenerator.getComparator(field).reversed());
                    break;
            }
        }

        Comparator<Product> generalComparator = comparators.get(0);
        for (int i = 1; i < comparators.size(); i++) {
            generalComparator = generalComparator.thenComparing(comparators.get(i));
        }
        return generalComparator;
    }

    public void printTopProducts() {
        Comparator<Product> comparator = Comparator.comparing(Product::getRate).reversed();
        for (Category category : categoryList) {
            System.out.println(category.getName());
            category.getProductList().sort(comparator);
            for (int i = 0; i < TOP_PRODUCTS_NUMBER; i++) {
                System.out.println(category.getProductList().get(i));
            }
        }
    }
}


