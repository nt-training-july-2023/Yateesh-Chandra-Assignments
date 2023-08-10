package Files;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class DataOutputStreamExample {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		FileOutputStream file = new FileOutputStream("D:\\testout.txt");
		DataOutputStream data = new DataOutputStream(file);
		data.writeInt(65);
		data.flush();
		data.close();
		System.out.println("Success...!");
		
	}

	
}
