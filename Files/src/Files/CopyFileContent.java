package Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CopyFileContent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			File data = new File("D:\\test.txt");
			FileInputStream fis = new FileInputStream (data);
			
			
			FileWriter file = new FileWriter("D:\\ot.txt");
			PrintWriter printer = new PrintWriter(file);
			
			int i = 0;
			while((i=fis.read())!=-1) {
				printer.print((char)i);
			}
			
			System.out.println("The items are successfully copied from test.txt to ot.txt");
			
			printer.close();
			fis.close();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
