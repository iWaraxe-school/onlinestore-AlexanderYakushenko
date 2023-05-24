package org.issoft;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Cart {
   // private static List<Product> goodsInCart = new CopyOnWriteArrayList<>();
   //private static List<Product> goodsInCart;
   private static Queue<Product> goodsInCart;
    static {
      goodsInCart = new ConcurrentLinkedQueue<>();
    }
    public static void addProduct(Product product) {
        goodsInCart.add(product);
    }
    public static void clearCart() {
        goodsInCart.clear();
    }
}
