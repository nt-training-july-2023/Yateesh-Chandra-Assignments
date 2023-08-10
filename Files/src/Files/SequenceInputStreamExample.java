package Files;

import java.io.FileInputStream;
import java.io.SequenceInputStream;

public class SequenceInputStreamExample {

	public static void main(String args[]) throws Exception {
		FileInputStream fin1 = new FileInputStream("D:\\test.txt");
		FileInputStream fin2 = new FileInputStream("D:\\test1.txt");
		SequenceInputStream sis = new SequenceInputStream(fin1, fin2);
		
		int j;
		while((j =sis.read())!= -1) {
			System.out.print((char)j);
		}
		sis.close();
		fin1.close();
		fin2.close();
	}
}
