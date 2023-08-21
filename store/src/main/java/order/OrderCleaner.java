package order;
import lombok.SneakyThrows;
import org.issoft.Cart;

import java.util.concurrent.TimeUnit;

public class OrderCleaner implements Runnable {

    @Override
    public void run() {
            cleanProcessWait();
            Cart.clearCart();
    }
@SneakyThrows
    private void cleanProcessWait() {
            TimeUnit.SECONDS.sleep(2);
    }
}
