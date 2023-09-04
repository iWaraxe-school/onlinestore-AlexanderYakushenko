package order;
import lombok.SneakyThrows;
import org.issoft.Cart;
import java.util.logging.Logger;

import java.util.concurrent.TimeUnit;

public class OrderCleaner implements Runnable {
    private final long delayInSeconds;
    public OrderCleaner(long delayInSeconds) {
        this.delayInSeconds = delayInSeconds;
    }

    private static final Logger logger = Logger.getLogger(OrderCleaner.class.getName());

    @SneakyThrows
    private void cleanProcessWait() {
            TimeUnit.SECONDS.sleep(delayInSeconds);
    }
    @Override
    public void run() {
        cleanProcessWait();
        Cart.clearCart();
        logger.info("Cart has been cleared");
    }
}
