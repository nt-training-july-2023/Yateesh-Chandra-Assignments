package FileOperations;
import java.io.*;
import java.util.Scanner;

public class CreateAFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Scanner s = new Scanner(System.in);
			
			System.out.println("Enter the name of a file (filename.extension): ");
			String fileName = s.nextLine();

			File myObj = new File(fileName);
			
			if(myObj.createNewFile()) {
				System.out.println("File created : " + myObj.getName());
			}
			
			else {
				System.out.println("This File already Exists ..!");
			}
			s.close();
		}
		catch(IOException e) {
			System.out.println("An error occured..!");
			e.printStackTrace();
		}
	}

}
