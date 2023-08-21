package org.issoft;
import db.DBHelper;

import java.sql.SQLException;

import static db.DBHelper.*;

public class StoreApp {

    public static void main(String[] args) throws SQLException {
        Store store = Store.getStore();
        RandomStorePopulator populator = new RandomStorePopulator(store);
        populator.fillStore();
        DBHelper dbHelper = new DBHelper(URL, USER, PASSWORD);

        //Scanner class used for input commands to get required result. (we can type the following: sort, top, quit)

        UserInputHandler input = new UserInputHandler();
        Runtime.getRuntime().addShutdownHook(new Thread(store::shutdown));

            boolean isRunning = true;
            boolean isOrderCleanerOn = false;
        System.out.println("введи одну из команд: sort, top, create order, quit");
        while(isRunning)
            {
                    switch (input.getNextInput ()) {
                        case "sort":
                            store.printData();
                            break;
                        case "top":
                            store.printTopProducts();
                            break;
                        case "create order":
                            store.createOrder();
                            if (!isOrderCleanerOn) {
                                store.startOrderCleaner();
                                isOrderCleanerOn = true;
                            }store.stop();
                            break;
                        case "quit":
                            isRunning = false;
                            store.shutdown();
                            break;
                        default:
                            System.out.println("Command is not supported.");
                    }
            }
    }
}