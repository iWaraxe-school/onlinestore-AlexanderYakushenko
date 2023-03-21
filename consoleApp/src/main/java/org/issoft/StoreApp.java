package org.issoft;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StoreApp {

    public static void main(String[] args) throws IOException {



        Store store = new Store();
        RandomStorePopulator populator = new RandomStorePopulator(store);
        populator.fillStore();

        //Scanner class used for input commands to get required result. (we can type the following: sort, top, quit)
        Scanner sc = new Scanner(new InputStreamReader(System.in));

            boolean isRunning = true;

        while(isRunning)

            {
                    switch (sc.nextLine()) {
                        case "sort":
                            store.printData();
                            break;
                        case "top":
                            store.printTopProducts();
                            break;
                        case "quit":
                            isRunning = false;
                            break;

                        default:
                            System.out.println("Command is not supported.");
                    }
            }
                //  store.sortByXml();
                //  store.printData();
                //  System.out.println("---------------");
                //  store.printTopProducts();
    }
}