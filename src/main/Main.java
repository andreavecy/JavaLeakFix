package main;

//imports

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        FileInputStream input = null;
        Scanner scanner = null;

        try{
            input = new FileInputStream("src/inputData/input.txt");
            scanner = new Scanner(input, "UTF-8");

            while (scanner.hasNextLine()){
                String text = scanner.nextLine();
                state(text);
            }
            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static void state(String text) throws IOException{
        new File("src/output").mkdirs();
        new File("src/output/city").mkdirs();
        new File("src/output/id").mkdirs();

        if (text.substring(0,1).equals("D")){
            newCity(text.split(";|,")[1].trim(), text.split(";|,")[0].substring(2).trim(), text.split(";|,")[2].trim());
            newId(text.split(";|,")[1].trim() , text.split(";|,")[2].trim());
        }
    }

    private static void newCity(String city, String name, String id) throws IOException {
        Writer writer = new BufferedWriter(new FileWriter("src/output/city/"+city+".txt", true));
        writer.append(name+", "+id);
        ((BufferedWriter) writer).newLine();
        writer.close();

    }

    private static void newId(String city, String id) throws IOException {
        Writer writer = new BufferedWriter(new FileWriter("src/output/id/"+id+".txt", true));
        writer.append(city);
        ((BufferedWriter) writer).newLine();
        writer.close();
    }
}
