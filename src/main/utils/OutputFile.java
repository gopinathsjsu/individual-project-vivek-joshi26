package main.utils;

import com.opencsv.CSVWriter;
import main.model.OutputOrder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class OutputFile {

    public static void outputErrorTextFile(String errorMessage) {
        try {
            FileWriter myWriter = new FileWriter("output_error.txt");
            myWriter.write(errorMessage);
            myWriter.close();
            System.out.println("Successfully wrote to the error file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static void outputSuccessFile(List<OutputOrder> outputOrders) {
        try {
            Random rand = new Random(); //instance of random class
            int upperbound = 1000;
            //generate random values from 0-24
            int int_random = rand.nextInt(upperbound);
            FileWriter outputFile = new FileWriter(int_random + "_output.csv");
            CSVWriter csvWriter = new CSVWriter(outputFile);
            String[] header = { "Item", "Quantity", "Price", "TotalPrice" };
            csvWriter.writeNext(header);

            int totalPrice = 0;
            for (OutputOrder order: outputOrders
                 ) {
                totalPrice += order.getTotalPrice();
            }


            OutputOrder firstOrder = outputOrders.get(0);
            String[] data1 = { firstOrder.getItem(), String.valueOf(firstOrder.getQuantity()), String.valueOf(firstOrder.getPrice()), String.valueOf(totalPrice)};
            csvWriter.writeNext(data1);
            outputOrders.remove(0);
            for (OutputOrder order:outputOrders
                 ) {
                String[] data2 = { order.getItem(), String.valueOf(order.getQuantity()), String.valueOf(order.getPrice())};
                csvWriter.writeNext(data2);
            }
            csvWriter.close();
            System.out.println("Successfully wrote the output file.");
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
