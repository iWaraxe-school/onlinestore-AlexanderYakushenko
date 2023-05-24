package org.issoft;
import lombok.SneakyThrows;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Order extends Thread {
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
