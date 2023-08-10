package Files;

import java.io.FileOutputStream;

public class FileOutputStreamExample1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			FileOutputStream fout = new FileOutputStream("D://testout.txt");
			fout.write(5);
			fout.close();
			System.out.println("Success.....");
			
		}
		catch(Exception e) {
			
			System.err.println(e);
		}
	}

}
