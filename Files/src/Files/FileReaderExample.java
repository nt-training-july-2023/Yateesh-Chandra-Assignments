package Files;

import java.io.FileReader;

public class FileReaderExample {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("D:\\test.txt");
		int i;
		while((i=fr.read())!= -1) {
			System.out.print((char)i);
		}
		fr.close();
	}
}
