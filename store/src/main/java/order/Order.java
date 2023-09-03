package order;
import lombok.SneakyThrows;
import org.issoft.Cart;
import org.issoft.Product;
import org.issoft.Store;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Order implements Runnable {
    
    private final OrderService orderService;

    public Order(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void run() {
        orderProcessWait();
        Store store = Store.getStore();
        List<Product> allProducts = store.getAllProducts();
        Product product = allProducts.get((int) (Math.random() * allProducts.size()));
        Cart.addProduct(product);
    }
@SneakyThrows
    private void orderProcessWait() {
        TimeUnit.SECONDS.sleep((long) (Math.random()*3+1));
    }
}
