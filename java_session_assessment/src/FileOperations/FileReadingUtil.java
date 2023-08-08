package FileOperations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReadingUtil {

    public static void readFile(String filename) {
        BufferedReader br = null;

        try {
            // Open the file for reading
            br = new BufferedReader(new FileReader(filename));

            // Read and process the contents of the file
            String line;
            while ((line = br.readLine()) != null) {
                // Process each line as needed (you can replace this with your logic)
                System.out.println(line);
            }
        } catch (IOException e) {
            // Handle file-related exceptions (if needed)
            System.out.println("Error: An I/O error occurred while reading the file.");
        } finally {
            // Close the file in the finally block to ensure proper cleanup
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error: Unable to close the file properly.");
                }
            }
        }
    }

    public static void main(String[] args) {
        // Get the file path from the user (you can use command-line arguments or Scanner for user input)
        String filePath = "file1.txt";

        // Call the method to read the file
        readFile(filePath);
    }
}
