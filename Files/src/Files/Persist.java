package Files;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Persist {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Student s1 = new Student(211,"Ravi");
			FileOutputStream fout = new FileOutputStream("y.txt");
			ObjectOutputStream out = new ObjectOutputStream(fout);
//			byte[] bytes = content.getBytes("UTF-8");
			out.writeObject(s1);
			
			out.flush();
			out.close();
			
			System.out.println("Success..!");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
