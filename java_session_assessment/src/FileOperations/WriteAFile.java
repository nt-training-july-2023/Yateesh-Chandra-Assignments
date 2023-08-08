package FileOperations;
import java.io.*;
import java.util.*;

public class WriteAFile {

	public static void main(String[] args) {
		
		try {
		FileWriter fw = new FileWriter("filename.txt");
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the message to input into the file");
		String message = s.nextLine();
		fw.write(message);
		fw.close();
		System.out.println("The file is successfully modified..!");
		
		s.close();
		}
		
		catch(IOException e) {
			System.out.println("An error occured..!");
			e.printStackTrace();
		}
		
	}

}
