package main.controller;

import main.iteratorPattern.Iterator;
import main.iteratorPattern.OrderRepository;
import main.utils.FileHandler;
import main.warehouse.Inventory;

import java.nio.file.Path;
import java.util.ArrayList;

public class OrderController {

    private Inventory inventoryDB = Inventory.getInstance();

    private FileHandler fileHandler;

    public OrderController(String path){
        fileHandler = new FileHandler(Path.of(path));
    }

    public void getOrder(){
        ArrayList<String> items = new ArrayList<>();
        fileHandler.ReadFile(items);

        OrderRepository orderRepository = new OrderRepository(items);
        Iterator iterator = orderRepository.getIterator();
        while (iterator.hasNext()){
            Object current = iterator.next();
            System.out.println("CURRENT : " + current);

            if(current != null){
                String[] individualItems = ((String)current).split(",");
                System.out.println("CURRENT individual array length: " + individualItems.length);
                if(individualItems.length == 3){
                    // WHEN CREDIT CARD IS PASSED IN THE ORDER
                }
                else {
                    // when no credit card is passed
                }
            }

        }



        /*
        for (String item: items
        ) {
            if (item == null)
                continue;
            System.out.println("Item: " + item);

            String[] individualItems = item.split(",");
            System.out.println("IndividualItems length:" +individualItems.length);

        }


         */

        //System.out.println(items);
    }

}
