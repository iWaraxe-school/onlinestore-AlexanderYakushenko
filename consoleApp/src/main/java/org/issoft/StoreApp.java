package org.issoft;

import java.util.Map;

public class StoreApp {

    public static void main(String[] args) {

        Store store = new Store();
        RandomStorePopulator populator = new RandomStorePopulator(store);
        populator.fillStore();
        //store.printAllProducts();

        store.sortByXml();
        store.printData();
        System.out.println("---------------");
        store.printTopProducts();
    }
}

