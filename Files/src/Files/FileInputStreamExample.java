package Files;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;

public class FileInputStreamExample {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		File data = new File("D:\\test.txt");
		FileInputStream file = new FileInputStream(data);
		FilterInputStream filter = new BufferedInputStream(file);
		int k = 0;
			while((k=filter.read())!=-1) {
				System.out.print((char)k);
			}
			file.close();
			filter.close();
		}
}
