package Files;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class DataInputStreamExample {
	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception{

		FileInputStream input = new FileInputStream("D:\\test1.txt");
		
		DataInputStream dis = new DataInputStream(input);
		
		int count = input.available();
		byte[] array = new byte[count];
		
		dis.read(array);
		
		for(byte bt : array) {
			
			char k = (char)bt;
			System.out.print(k+"-");
			
		}
}
}
