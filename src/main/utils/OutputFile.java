package main.utils;

import com.opencsv.CSVWriter;
import main.model.OutputOrder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
            FileWriter outputFile = new FileWriter("output.csv");
            CSVWriter csvWriter = new CSVWriter(outputFile);
            String[] header = { "Item", "Quantity", "Price", "TotalPrice" };
            csvWriter.writeNext(header);
            OutputOrder firstOrder = outputOrders.get(0);
            String[] data1 = { firstOrder.getItem(), String.valueOf(firstOrder.getQuantity()), String.valueOf(firstOrder.getPrice()), String.valueOf(OutputOrder.getTotalPrice())};
            csvWriter.writeNext(data1);
            outputOrders.remove(0);
            for (OutputOrder order:outputOrders
                 ) {
                String[] data2 = { order.getItem(), String.valueOf(order.getQuantity()), String.valueOf(order.getPrice())};
                csvWriter.writeNext(data2);
            }
            csvWriter.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
