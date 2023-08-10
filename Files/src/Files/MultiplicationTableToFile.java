package Files;

import java.io.*;
import java.util.Scanner;

public class MultiplicationTableToFile {
    public static void main(String[] args) {
    	
    	Scanner s = new Scanner(System.in);
    	System.out.println("Enter a number to print its multiplication table : ");
        int number = s.nextInt(); // Change this to the desired number for the multiplication table

        try {

        
        	
            FileWriter writer = new FileWriter("output.txt");
            
            PrintWriter printWriter = new PrintWriter(writer);

            printWriter.println("Multiplication Table for " + number + ":");

            for (int i = 1; i <= 10; i++) {
                int result = number * i;
                printWriter.println(number + " * " + i + " = " + result);
            }

            printWriter.close();
            writer.close();

            System.out.println("Multiplication table has been written to output.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.close();
    }
}

