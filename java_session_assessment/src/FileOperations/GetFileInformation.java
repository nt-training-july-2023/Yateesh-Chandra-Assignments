package FileOperations;
import java.io.*;
import java.util.Scanner;

public class GetFileInformation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		System.out.println("Enter the File Name : ");
		String fileName = s.nextLine();		
		File myObj = new File(fileName);
		if(myObj.exists()) {
			System.out.println("File name : "+myObj.getName());
			System.out.println("Absolute Path : " + myObj.getAbsolutePath());
			System.out.println("Writeable : "+ myObj.canWrite());
			System.out.println("Readable : " + myObj.canRead());
			System.out.println("File size in Bytes : " + myObj.length());
		}
		else {
			System.out.println("The file does not exist..!");
		}
		s.close();
	}

}
