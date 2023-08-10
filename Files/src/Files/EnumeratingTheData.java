package Files;

import java.io.FileInputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

public class EnumeratingTheData {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		FileInputStream fin1 = new FileInputStream("D:\\a.txt");
		FileInputStream fin2 = new FileInputStream("D:\\b.txt");
		FileInputStream fin3 = new FileInputStream("D:\\c.txt");
		FileInputStream fin4 = new FileInputStream("D:\\d.txt");
		
		Vector v = new Vector();
		
		v.add(fin1);
		v.add(fin2);
		v.add(fin3);
		v.add(fin4);
		
		Enumeration e = v.elements();
		SequenceInputStream bin = new SequenceInputStream(e);
		
		int i = 0;
		while((i = bin.read())!= -1) {
			System.out.print((char)i);
		}
		
		bin.close();
		fin1.close();
		fin2.close();
	}

}
