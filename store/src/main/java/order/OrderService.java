package order;

import org.issoft.Cart;
import org.issoft.Product;
import org.issoft.Store;

import java.util.List;

public class OrderService {
    public void processOrder(Store store, Cart cart) {
        Product product = selectRandomProduct(store);
        cart.addProduct(product);
    }

    private Product selectRandomProduct(Store store) {
        List<Product> allProducts = store.getAllProducts();
        if (allProducts.isEmpty()) {
            return null;
        }
        return allProducts.get((int) (Math.random() * allProducts.size()));
    }
}
