package org.issoft;
import category.CategoryPrinter;
import order.Order;
import order.OrderCleaner;
import product.ProductComparatorGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Store {
    private static final Logger logger = LoggerFactory.getLogger(Store.class);
    private ExecutorService executorService;
    private ScheduledExecutorService scheduledExecutorService;
    private static final Store store = new Store();
    private static final int TOP_PRODUCTS_NUMBER = 5;
    private final List<Category> categoryList = new ArrayList<>();

    private static final int SHUTDOWN_TIMEOUT = 60;

    public Store() {
        this.executorService = Executors.newCachedThreadPool();
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
    }
    public static Store getStore(){
        return store;
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
        try {
            executorService.shutdown(); // Disable new tasks from being submitted

                // Wait a while for existing tasks to terminate
                if (!executorService.awaitTermination(SHUTDOWN_TIMEOUT, TimeUnit.SECONDS)) {
                    executorService.shutdownNow(); // Cancel currently executing tasks
                    // Wait a while for tasks to respond to being cancelled
                    if (!executorService.awaitTermination(SHUTDOWN_TIMEOUT, TimeUnit.SECONDS))
                        logger.error("ExecutorService did not terminate");
                }
            } catch (InterruptedException ie) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
                logger.error("Shutdown interrupted", ie);
            }
        }

    public void addCategory(Category category) {
        categoryList.add(category);
    }
    public void printData() {
        for (Category category : categoryList) {
            logger.info("***************");
            logger.info(category.getName());
            logger.info("---------------");
            CategoryPrinter.printProducts(category.getProductList());
            logger.info("---------------");
        }
    }

    public void sortByXml() {
        Map<String, String> fieldToSort = XMLParser.parseXml();
        ProductSorter productSorter = new ProductSorter();
        for (Category category : categoryList) {
            category.getProductList().sort(productSorter.getComparator(fieldToSort));
        }
    }
    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        for (Category category : categoryList) {
            allProducts.addAll(category.getProductList());
        }
        return allProducts;
    }

//    public Comparator<Product> getComparator(Map<String, String> fieldToSort) {
//        List<Comparator<Product>> comparators = new ArrayList<>();
//        for (Map.Entry<String, String> entry : fieldToSort.entrySet()) {
//
//            Sorting sorting = Sorting.valueOf(entry.getValue());
//            String field = entry.getKey();
//
//            switch (sorting) {
//                case ASC:
//                    comparators.add(ProductComparatorGenerator.getComparator(field));
//                    break;
//                case DESC:
//                    comparators.add(ProductComparatorGenerator.getComparator(field).reversed());
//                    break;
//            }
//        }
//
//        Comparator<Product> generalComparator = comparators.get(0);
//        for (int i = 1; i < comparators.size(); i++) {
//            generalComparator = generalComparator.thenComparing(comparators.get(i));
//        }
//        return generalComparator;
//    }

    public void printTopProducts() {
        Comparator<Product> comparator = Comparator.comparing(Product::getRate).reversed();
        for (Category category : categoryList) {
            logger.info(category.getName());
            category.getProductList().sort(comparator);
            for (int i = 0; i < TOP_PRODUCTS_NUMBER && i < category.getProductList().size(); i++) {
                logger.info(category.getProductList().get(i).toString());
            }
        }
    }
}


