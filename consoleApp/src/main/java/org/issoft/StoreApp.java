package org.issoft;

public class StoreApp {

    public static void main(String[] args) {

       Store store = new Store();
       RandomStorePopulator populator = new RandomStorePopulator(store);
       populator.fillStore();

       store.printData();

        //store.printTopProducts();
        store.sortByXml();

    }
}

