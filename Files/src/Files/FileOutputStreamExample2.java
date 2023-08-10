package Files;

import java.io.FileOutputStream;
import java.util.Scanner;

public class FileOutputStreamExample2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			FileOutputStream fout = new FileOutputStream("D://test.txt");
			Scanner s = new Scanner(System.in);
			System.out.println("Enter the content : ");
			String str = s.nextLine();
			byte b[] = str.getBytes();
			fout.write(b);
			fout.close();
			System.out.println("Success....!");
			s.close();
		}
		
		catch(Exception e) {
			System.err.println(e);
		}
	}

}
