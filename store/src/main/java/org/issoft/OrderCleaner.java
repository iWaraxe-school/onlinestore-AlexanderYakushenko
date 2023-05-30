package org.issoft;
import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class OrderCleaner implements Runnable {
//public class OrderCleaner implements Callable {

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
