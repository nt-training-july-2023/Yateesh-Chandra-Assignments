package Files;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class BufferedOutputStreamExample {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		FileOutputStream fout = new FileOutputStream("D:\\test1.txt");
		BufferedOutputStream bout = new BufferedOutputStream(fout);
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a message : ");
		String str = s.nextLine();
		byte b[] = str.getBytes();
		bout.write(b);
		bout.flush();
		bout.close();
		fout.close();
		System.out.println("Success...!");
		s.close();
	}

}
