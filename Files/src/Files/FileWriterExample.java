package Files;

import java.io.*;

public class FileWriterExample {

	public static void main(String[] args) {
		try {
			FileWriter fw = new FileWriter("D:\\test.txt");
			fw.write("Welcome to World..!");
			fw.close();			
		}
		
		catch(Exception e) {
			System.out.println("e");
		}
		
		System.out.println("Success....!");

	}

}
