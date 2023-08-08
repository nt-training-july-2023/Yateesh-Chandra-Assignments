package FileOperations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileCopyExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the source file name: ");
        String sourceFile = scanner.nextLine();

        System.out.print("Enter the destination file name: ");
        String destinationFile = scanner.nextLine();

        try {
            // Open the source file for reading
            BufferedReader br = new BufferedReader(new FileReader(sourceFile));

            // Open the destination file for writing
            BufferedWriter bw = new BufferedWriter(new FileWriter(destinationFile));

            // Read and copy the content from the source file to the destination file
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }

            // Close the files
            br.close();
            bw.close();

            System.out.println("File copied successfully!");
        } catch (IOException e) {
            // Handle file-related exceptions
            if (e instanceof java.io.FileNotFoundException) {
                System.out.println("Error: The file '" + sourceFile + "' was not found.");
            } else if (e instanceof java.io.IOException) {
                System.out.println("Error: An I/O error occurred while reading or writing the files.");
            } else {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (SecurityException e) {
            System.out.println("Error: Permission denied to access the files.");
        } catch (Exception e) {
            System.out.println("Error: An unexpected exception occurred: " + e.getMessage());
        }
        
        scanner.close();
    }
}
