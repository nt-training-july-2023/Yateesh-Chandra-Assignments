package Files;

import java.io.*;

public class ByteArrayOutputStreamExample {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		try {
		FileOutputStream fout1 = new FileOutputStream("D:\\a.txt");
		FileOutputStream fout2 = new FileOutputStream("D:\\b.txt");
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		bout.write(65);
		bout.writeTo(fout1);
		bout.writeTo(fout2);
		
		bout.flush();
		bout.close();
		System.out.println("Success...!");
		}
		catch(Exception e) {
			System.out.print(e);
		}
	}

}
