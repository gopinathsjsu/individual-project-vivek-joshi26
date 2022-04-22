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
        //  /home/vivek/Documents/Subjects/202-Software Systems Engineering/individual-project-vivek-joshi26/Sample_Inventory_Input_Output.csv
        String stockFilePath = sc.nextLine();
        /*
        FileHandler fileHandler = new FileHandler(Path.of(stockFilePath));
        fileHandler.ReadFile(new ArrayList<>());
         */

        InventoryController inventoryController = new InventoryController(stockFilePath);
        inventoryController.updateInventoryDB();


        System.out.println("Enter the file path of orders: ");
        //  /home/vivek/Documents/Subjects/202-Software Systems Engineering/individual-project-vivek-joshi26/Order.csv
        String orderFilePath = sc.nextLine();

        OrderController orderController = new OrderController(orderFilePath);
        orderController.getOrder();

    }

}
