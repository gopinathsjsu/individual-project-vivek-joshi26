package main.utils;

import main.model.Category;
import main.model.Item;
import main.model.OutputOrder;
import main.warehouse.Inventory;

import java.util.*;

public class ValidateOrder {

    private Inventory inventoryDB = Inventory.getInstance();

    public String validateAvailableStock(HashMap<String, Integer> orderMap){
        Set<String> itemsNotAvailable = new HashSet<>();
        HashMap<String, Item> itemHashMap = inventoryDB.getItems();

        for (Map.Entry<String, Integer> entry :orderMap.entrySet()
             ) {
            Item temp = itemHashMap.getOrDefault(entry.getKey(), null);
            if(temp == null)
                itemsNotAvailable.add(entry.getKey());
            else {
                //Category category = temp.getCategory();
                //category.limitExceeded(entry.getValue()) ||
                if(temp.getQuantity() < entry.getValue())
                    itemsNotAvailable.add(entry.getKey());
            }

        }
        if(itemsNotAvailable.size() != 0){
            return "Please Correct Quantities: " + itemsNotAvailable.toString();
        }
        return "ValidQuantity";
    }

    public String validateCategoryLimit(HashMap<String, Integer> orderMap){

        Set<String> itemsNotAvailable = new HashSet<>();
        HashMap<String, Item> itemHashMap = inventoryDB.getItems();
        HashMap<String, Integer> categoryCountMap = new HashMap<>();

        for (Map.Entry<String, Integer> entry :orderMap.entrySet()
        ) {
            Item temp = itemHashMap.getOrDefault(entry.getKey(), null);
            System.out.println("TEMP ITEM VALUE : " + temp + " for entry : " + entry.getKey());
            if(temp == null)
                itemsNotAvailable.add(entry.getKey());
            else {
                Category category = temp.getCategory();



                int count = categoryCountMap.getOrDefault(category.getCategoryName(), 0) + entry.getValue();
                categoryCountMap.put(category.getCategoryName(), count );

                System.out.println(category.getCategoryName());
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
                System.out.println("For Key :" + category.getCategoryName() +" Value: " + count);

                if(category.limitExceeded(count))
                    itemsNotAvailable.add(entry.getKey());
            }

        }
        System.out.println(categoryCountMap);
        if(itemsNotAvailable.size() != 0){
            return "Please Correct Category Quantities: " + itemsNotAvailable.toString();
        }
        return "ValidQuantity";
    }

    // Get total cost
    public List<OutputOrder> totalPrice(HashMap<String, Integer> orderMap){
        List<OutputOrder> outputOrders = new ArrayList<>();

        HashMap<String, Item> itemHashMap = inventoryDB.getItems();

        System.out.println("************* " + orderMap);
        System.out.println("************* ItemHashMap" + itemHashMap);
        for (Map.Entry<String, Integer> entry :orderMap.entrySet()
        ) {
            Item tempItem = itemHashMap.get(entry.getKey());
            OutputOrder tempOrder = new OutputOrder();
            tempOrder.setItem(entry.getKey());
            tempOrder.setQuantity(entry.getValue());
            tempOrder.setPrice((int) (entry.getValue() * tempItem.getPrice()));
            OutputOrder.setTotalPrice((int) (OutputOrder.getTotalPrice() + entry.getValue() * tempItem.getPrice()));
            // update quantity in inventoryDB
            tempItem.setQuantity(tempItem.getQuantity() - entry.getValue());
            itemHashMap.put(entry.getKey(), tempItem);
            outputOrders.add(tempOrder);
        }
        inventoryDB.setItems(itemHashMap);
        System.out.println("**************** " + outputOrders.toString());
        System.out.println("************* ItemHashMap After order" + inventoryDB.getItems());
        return outputOrders;
    }

}
