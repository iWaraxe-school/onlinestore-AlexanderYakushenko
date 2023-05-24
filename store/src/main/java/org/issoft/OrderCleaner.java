package org.issoft;
import lombok.SneakyThrows;
import java.util.concurrent.TimeUnit;

public class OrderCleaner implements Runnable {

    @Override
    public void run() {
        while (true) {
            cleanProcessWait();
            Cart.clearCart();
        }
    }
@SneakyThrows
    private void cleanProcessWait() {
            TimeUnit.SECONDS.sleep(2);
    }

}
