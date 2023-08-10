package Files;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class BufferedInputStreamExample1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			FileInputStream fin = new FileInputStream("D:\\test.txt");
			BufferedInputStream bin = new BufferedInputStream(fin);
			int i;
			while((i=bin.read())!= -1) {
				System.out.print((char)i);
			}
			bin.close();
			fin.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
