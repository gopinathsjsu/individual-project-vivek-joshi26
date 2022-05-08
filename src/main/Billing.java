package main;

import main.controller.InventoryController;
import main.controller.OrderController;
import main.utils.FileHandler;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Billing {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file path of stock inventory: ");
        //  /home/vivek/Documents/Subjects/202-Software Systems Engineering/individual-project-vivek-joshi26/Inventory.csv
        String stockFilePath = sc.nextLine();
        /*
        FileHandler fileHandler = new FileHandler(Path.of(stockFilePath));
        fileHandler.ReadFile(new ArrayList<>());
         */

        InventoryController inventoryController = new InventoryController(stockFilePath);
        inventoryController.updateInventoryDB();

        Boolean flag = true;
        while (flag == true){
            System.out.println("Enter the file path of orders: ");
            String orderFilePath = sc.nextLine();
            OrderController orderController = new OrderController(orderFilePath);
            orderController.getOrder();

            System.out.println("\n");
            System.out.println("Do you want to process more orders, enter yes for confirmation and no for termination of the program: ");
            String response = sc.nextLine();
            if(response.equalsIgnoreCase("no"))
                flag = false;
        }

//        System.out.println("Enter the file path of orders: ");
//        //  /home/vivek/Documents/Subjects/202-Software Systems Engineering/individual-project-vivek-joshi26/Input_order_1.csv
//        String orderFilePath = sc.nextLine();
//        OrderController orderController = new OrderController(orderFilePath);
//        orderController.getOrder();

    }

}
