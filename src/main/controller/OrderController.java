package main.controller;

import main.iteratorPattern.Iterator;
import main.iteratorPattern.OrderRepository;
import main.model.OutputOrder;
import main.utils.FileHandler;
import main.utils.OutputFile;
import main.utils.ValidateOrder;
import main.warehouse.Inventory;

import java.nio.file.Path;
import java.util.*;

public class OrderController {

    private Inventory inventoryDB = Inventory.getInstance();

    private FileHandler fileHandler;

    public OrderController(String path){
        fileHandler = new FileHandler(Path.of(path));
    }

    public void getOrder(){
        ArrayList<String> items = new ArrayList<>();
        HashMap<String, Integer> orderMap = new HashMap<>();
        fileHandler.ReadFile(items);
        String creditCard = null;

        OrderRepository orderRepository = new OrderRepository(items);
        Iterator iterator = orderRepository.getIterator();
        while (iterator.hasNext()){
            Object current = iterator.next();
            //System.out.println("CURRENT : " + current);

            if(current != null){
                String[] individualItems = ((String)current).split(",");
                System.out.println("CURRENT individual array length: " + individualItems.length);
                if(individualItems.length == 3){
                    // WHEN CREDIT CARD IS PASSED IN THE ORDER
                    orderMap.put(individualItems[0],Integer.parseInt(individualItems[1]));
                    creditCard = individualItems[2];
                    //****************** ADD CREDIT CARD TO CARD DETAILS SET
                }
                else {
                    // when no credit card is passed
                    orderMap.put(individualItems[0],Integer.parseInt(individualItems[1]));
                }
            }
            //System.out.println("orderMap : " + orderMap);
        }

        ValidateOrder validateOrder = new ValidateOrder();
        String quantityValidateOutput = validateOrder.validateAvailableStock(orderMap);
        //System.out.println(quantityValidateOutput);
        String categoryValidateOutput = validateOrder.validateCategoryLimit(orderMap);
        //System.out.println(categoryValidateOutput);
        if(quantityValidateOutput.equals("ValidQuantity") && categoryValidateOutput.equals("ValidQuantity")){
            // Calculate Prices and generate Bill
            List<OutputOrder> outputOrders = validateOrder.totalPrice(orderMap);
            OutputFile.outputSuccessFile(outputOrders);

            // Check credit card is present if not add
            System.out.println("CREDIT CARD NUMBER: " + creditCard);
            Set<String> cards = inventoryDB.getCards();
            if(cards != null && !cards.contains(creditCard)) {
                cards.add(creditCard);
                inventoryDB.setCards((HashSet<String>) cards);
            }

        }
        else if( !categoryValidateOutput.equals("ValidQuantity")){
            OutputFile.outputErrorTextFile(categoryValidateOutput);
        }
        else {
            // generate error Output File
            OutputFile.outputErrorTextFile(quantityValidateOutput);
        }

    }

}
