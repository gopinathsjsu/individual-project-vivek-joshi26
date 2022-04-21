package main.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileHandler {

    Path path;

    public FileHandler(Path path) {
        this.path = path;
    }

    public void ReadFile(ArrayList<String> content){
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(path.toFile()));
            String line = bufferedReader.readLine();
            while (line != null){
                line = bufferedReader.readLine();
                content.add(line);
            }
            bufferedReader.close();
            /*
            System.out.println("Content in arraylist:");

            for (String item:content
                 ) {
                System.out.println(item);
            }
            */


        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
