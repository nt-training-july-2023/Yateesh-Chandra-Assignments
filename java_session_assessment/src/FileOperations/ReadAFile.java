package FileOperations;
import java.io.*;
import java.util.*;

public class ReadAFile {

	public static void main(String args[]) {
		
		try {
			Scanner sc = new Scanner (System.in);
			System.out.println("Enter the Filename to read its contents :");
			String fileName = sc.nextLine();
			File myObj = new File(fileName+".txt");
			Scanner s = new Scanner(myObj);
			while(s.hasNextLine()) {
				String data =s.nextLine();
				System.out.println(data);
			}
			s.close();
			sc.close();
		}
		
		catch(FileNotFoundException e) {
			System.out.println("An Error occured..!");
			e.printStackTrace();
		}
	}
}
