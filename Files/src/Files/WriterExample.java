package Files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriterExample {
	public static void main(String args[]) {
		try {
			FileWriter w = new FileWriter("output.txt");
			Scanner s = new Scanner(System.in);
			System.out.println("Enter the content : ");
			String content = s.nextLine();
			w.write(content);
			w.close();
			System.out.println("Done..!");
			s.close();
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
