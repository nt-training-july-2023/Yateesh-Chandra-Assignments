package Files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.util.Scanner;

public class FilterOutputStreamExample {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		File data = new File("D:\\test.txt");
		FileOutputStream file = new FileOutputStream(data);
		
		FilterOutputStream filter = new FilterOutputStream(file);
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		
		byte b[] = str.getBytes();
		filter.write(b);;
		filter.flush();
		filter.close();
		file.close();
		
		System.out.println("Success...!");
		s.close();
	}

}
