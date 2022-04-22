package main.controller;

import main.iteratorPattern.Iterator;
import main.iteratorPattern.OrderRepository;
import main.model.Category;
import main.model.Item;
import main.utils.FileHandler;
import main.warehouse.Inventory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InventoryController {

    private Inventory inventoryDB = Inventory.getInstance();

    private FileHandler fileHandler;

    public InventoryController(String path){
        fileHandler = new FileHandler(Path.of(path));
    }

    public void updateInventoryDB(){
        ArrayList<String> items = new ArrayList<>();
        fileHandler.ReadFile(items);

        OrderRepository orderRepository = new OrderRepository(items);
        Iterator iterator = orderRepository.getIterator();

        while (iterator.hasNext()){
            Object current = iterator.next();
            //System.out.println("CURRENT Inventory: " + current);
            if(current == null)
                continue;
            String[] individualItems = ((String)current).split(",");

            if (individualItems.length > 2) {
                //System.out.println("individualItem: " + individualItems[1]);
                if (individualItems[1].equals("Category"))
                    continue;
                Category category = Category.Create(individualItems[1]);
                //System.out.println("category: " + category.toString());
                inventoryDB.getItems().put(individualItems[0], new Item(category, individualItems[0], Integer.parseInt(individualItems[2]), Double.parseDouble(individualItems[3])));

            }
            else {
                //System.out.println("CARD DETAILS ******************** : " + item);
                String[] individualCards = ((String)current).split(",");
                //System.out.println("individualCards CARD length ******************** : " + individualCards.length);
                if(individualCards.length == 1 && !individualCards[0].equals("Cards")  && !individualCards[0].equals("CardNumber")){
                    //System.out.println("*********************** CARDNUMBER: " + individualCards[0]);
                    inventoryDB.getCards().add(individualCards[0]);
                }

            }
        }

        HashMap<String, Item> itemHashMap = inventoryDB.getItems();
        for (Map.Entry<String, Item> entry :itemHashMap.entrySet()
        ) {
            Item temp = entry.getValue();
            System.out.println("key : " + entry.getKey() + " VALUE: " + temp.toString());
        }

        System.out.println("CARDS: " + inventoryDB.getCards() );


    }

    /*
    public void updateInventoryDB(){

        ArrayList<String> items = new ArrayList<>();
        fileHandler.ReadFile(items);
        for (String item: items
             ) {
            if (item == null)
                continue;

            //System.out.println("item: " + item);
            String[] individualItems = item.split(",");
            if (individualItems.length > 2) {
                //System.out.println("individualItem: " + individualItems[1]);
                if (individualItems[1].equals("Category"))
                    continue;
                Category category = Category.Create(individualItems[1]);
                //System.out.println("category: " + category.toString());
                inventoryDB.getItems().put(individualItems[0], new Item(category, individualItems[0], Integer.parseInt(individualItems[2]), Double.parseDouble(individualItems[3])));

            }
            else {
                //System.out.println("CARD DETAILS ******************** : " + item);
                String[] individualCards = item.split(",");
                //System.out.println("individualCards CARD length ******************** : " + individualCards.length);
                if(individualCards.length == 1 && !individualCards[0].equals("Cards")  && !individualCards[0].equals("CardNumber")){
                    //System.out.println("*********************** CARDNUMBER: " + individualCards[0]);
                    inventoryDB.getCards().add(individualCards[0]);
                }

            }
        }
        //System.out.println(inventoryDB.getItems());
        HashMap<String, Item> itemHashMap = inventoryDB.getItems();
        for (Map.Entry<String, Item> entry :itemHashMap.entrySet()
             ) {
            Item temp = entry.getValue();
            System.out.println("key : " + entry.getKey() + " VALUE: " + temp.toString());
        }

        System.out.println("CARDS: " + inventoryDB.getCards() );

    }

     */


}
