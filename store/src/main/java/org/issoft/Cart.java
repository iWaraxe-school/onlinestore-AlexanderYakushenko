package org.issoft;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cart {
    private static List<Product> goodsInCart = new CopyOnWriteArrayList<>();

    public static void addProduct(Product product) {
        goodsInCart.add(product);
    }
    public static void clearCart() {
        goodsInCart.clear();
    }
}
