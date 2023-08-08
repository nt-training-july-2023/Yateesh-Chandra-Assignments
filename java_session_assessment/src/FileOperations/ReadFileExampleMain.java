package FileOperations;

import java.io.*;


public class ReadFileExampleMain {
    public static void main(String[] args) {
        // Get the file path from the user (you can use command-line arguments or Scanner for user input)
        String filePath = "filename.txt";

        try {
            // Open the file for reading
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            // Read and print the contents of the file
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            // Close the file
            br.close();
        } catch (IOException e) {
            // Handle file-related exceptions
            if (e instanceof java.io.FileNotFoundException) {
                System.out.println("Error: The file '" + filePath + "' was not found.");
            } else if (e instanceof java.io.IOException) {
                System.out.println("Error: An I/O error occurred while reading the file.");
            } else {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (SecurityException e) {
            System.out.println("Error: Permission denied to access the file.");
        } catch (Exception e) {
            System.out.println("Error: An unexpected exception occurred: " + e.getMessage());
        }
    }
}
